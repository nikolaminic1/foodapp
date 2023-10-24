package com.example.foodapp.product.service.business.implementation;

import com.example.foodapp.auth.repo.UserRepository;
import com.example.foodapp.auth.service._UserProfileService;
import com.example.foodapp.auth.user.User;
import com.example.foodapp.product.model.Product;
import com.example.foodapp.product.model.ProductCategory;
import com.example.foodapp.product.model.Request.ProductRequest;
import com.example.foodapp.product.repo.ProductCategoryRepo;
import com.example.foodapp.product.repo.ProductRepo;
import com.example.foodapp.product.service.business.OwnerProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
@Log4j2
public class OwnerProductServiceImplementation implements OwnerProductService {
    private final ProductRepo productRepo;
    private final ProductCategoryRepo productCategoryRepo;
    private final UserRepository userRepo;
    private final _UserProfileService userProfileService;
    private long productCategoryId;

    @Override
    public Collection<Product> getMyList(Principal principal) {
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
    public Collection<Product> list(int limit) {
        return null;
    }

    @Override
    public Product get(Long id) {
        if(productRepo.findById(id).isPresent()){
            return productRepo.findById(id).get();
        }
        return null;
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
