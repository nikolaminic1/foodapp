package com.example.foodapp.product.service.business.implementation;

import com.example.foodapp._api.PaginatedResponse;
import com.example.foodapp.auth.repo.UserRepository;
import com.example.foodapp.auth.service._UserProfileService;
import com.example.foodapp.auth.user.User;
import com.example.foodapp.business.model.Business;
import com.example.foodapp.business.repo.BusinessRepo;
import com.example.foodapp.business.serializers.BusinessListSerializer;
import com.example.foodapp.product.model.Product;
import com.example.foodapp.product.model.ProductCategory;
import com.example.foodapp.product.model.Request.ProductRequest;
import com.example.foodapp.product.repo.ProductCategoryRepo;
import com.example.foodapp.product.repo.ProductRepo;
import com.example.foodapp.product.serializers.admin.AdminProductSerializer;
import com.example.foodapp.product.serializers.restaurant.RestaurantProductSerializer;
import com.example.foodapp.product.service.business.OwnerProductService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.util.Assert;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.*;

import static com.example.foodapp._api.NumericCheck.isNumeric;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RequiredArgsConstructor
@Service
@Transactional
@Log4j2
public class OwnerProductServiceImplementation implements OwnerProductService {
    private final ProductRepo productRepo;
    private final ProductCategoryRepo productCategoryRepo;
    private final UserRepository userRepo;
    private final _UserProfileService userProfileService;
    private final BusinessRepo businessRepo;
    private long productCategoryId;

    @Override
    public List<Product> getMyList(Principal principal) {
        productRepo.findAll();
        return null;
    }

    @Override
    public Product create(ProductRequest productRequest, Principal principal) {
        try{
            long productCategoryId = productRequest.getProductCategory();
            User user = userRepo.findByEmail(principal.getName()).orElseThrow();

            if(productCategoryRepo.findById(productCategoryId).isPresent()){
                ProductCategory productCategory = productCategoryRepo.findById(productCategoryId).get();
//                if(productCategory.getBusiness() == userProfileService.returnBusinessOwnerProfile(user).getBusiness()){
                    Product product = new Product();

                    // Cannot invoke "app.web.business.model.Business.getBusinessOwner()" because the return value of "app.web.product.model.ProductCategory.getBusiness()" is null

                    product.setAboutProduct(productRequest.getAboutProduct());
                    product.setCodeOfProduct(productRequest.getCodeOfProduct());
                    product.setPriceOfProduct(productRequest.getPriceOfProduct());
                    product.setIsOnDiscount(productRequest.isOnDiscount());
                    product.setPreparationTime(productRequest.getPreparationTime());
                    product.setAvailability(productRequest.getAvailability());
                    product.setProductVisible(productRequest.isProductVisible());
                    product.setProductCategory(productCategory);

                    return productRepo.save(product);

//            String path = "/business/product/product_detail/product/" + product.getId();

//                         it need to get id of product and save it uri

//            UriComponents uriComponents = UriComponentsBuilder.newInstance().scheme("http").host("localhost:8070")
//                    .path(path).build().encode();

//            product.setUri(uriComponents.toUri());

//                        Optional<ProductCategory> productCategory = productCategoryRepo.findById(productRequest.getProductCategory());


//                }

            }

            return null;

        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public PaginatedResponse<Product> list(String page, String per_page, String order, String visible, Principal principal) throws Exception {
        int page_n;
        int limit;
        int order_n;
        int visible_n;

        if (isNumeric(page)) {
            page_n = Integer.parseInt(page);
        } else {
            page_n = 0;
        }

        if (isNumeric(per_page)) {
            limit = Integer.parseInt(per_page);
        } else {
            limit = 20;
        }

        if (isNumeric(order)) {
            order_n = Integer.parseInt(order);
        } else {
            order_n = 3;
        }
        if (isNumeric(visible)) {
            visible_n = Integer.parseInt(visible);
        } else {
            visible_n = 0;
        }


        User user = userRepo.findByEmail(principal.getName())
                .orElseThrow(() -> new Exception("User not found"));
        Business business = businessRepo.findBusinessByBusinessOwner_User(user)
                .orElseThrow(() -> new Exception("Business not found"));

        Pageable pageable = switch (order_n) {
            case 2 -> PageRequest.of(page_n, limit, Sort.by("priceOfProduct").descending());
            case 3 -> PageRequest.of(page_n, limit, Sort.by("dateUpdated").ascending());
            case 4 -> PageRequest.of(page_n, limit, Sort.by("dataCreated").ascending());
            default -> PageRequest.of(page_n, limit, Sort.by("priceOfProduct").ascending());
        };

        Page<Product> productsPage;

        if (visible_n == 0) {
            productsPage = productRepo.findProductsByProductCategory_Business(business, pageable);
        } else {
            productsPage = productRepo
                    .findProductsByProductCategory_BusinessAndProductVisible(business, true, pageable);
        }

        PaginatedResponse<Product> data = new PaginatedResponse<>();
        data.setItems(productsPage.getContent());
        data.setCount(productsPage.getTotalElements());
        return data;
    }

    @Override
    public String get(Principal principal, Long id) throws Exception {
        User user = userRepo
                .findByEmail(principal.getName())
                .orElseThrow(() -> new Exception("User not found"));
        Product product = productRepo
                .findProductByIdAndProductCategory_Business_BusinessOwner_User(id, user)
                .orElseThrow((() -> new Exception("Product not found")));

        Collection<ProductCategory> categories = productCategoryRepo
                .findProductCategoriesByBusiness(product.getProductCategory().getBusiness());

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Product.class, new RestaurantProductSerializer.DetailSerializer());
        mapper.registerModule(module);
        JsonNode node = mapper.readTree(mapper.writeValueAsString(product));

        ArrayNode arrayNode = mapper.createArrayNode();

        for (var k : categories) {
            ObjectNode cat = mapper.createObjectNode();
            cat.put("categoryVisible", k.getCategoryVisible());
            cat.put("featured", k.getFeatured());
            cat.put("dateCreated", k.getDateCreated().toString());
            cat.put("descOfCategory", k.getDescOfCategory());
            cat.put("id", k.getId());
            cat.put("nameOfCategory", k.getNameOfCategory());
            cat.put("dateUpdated", k.getDateUpdated().toString());
            cat.put("businessId", k.getBusiness().getId());
            arrayNode.add(cat);
        }

        if (node.isObject()) {
            ObjectNode objectNode = (ObjectNode) node;
            objectNode.set("all_categories", arrayNode);
        }
        return mapper.writeValueAsString(node);

    }

    @Override
    public Product update(ProductRequest productRequest, Long id, Principal principal) {
        Product product = productRepo.findById(id).get();
        return productRepo.save(product);
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
