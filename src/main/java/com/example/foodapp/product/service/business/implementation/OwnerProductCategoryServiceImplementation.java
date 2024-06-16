package com.example.foodapp.product.service.business.implementation;

import com.example.foodapp.auth.repo.BusinessOwnerRepo;
import com.example.foodapp.auth.repo.UserRepository;
import com.example.foodapp.auth.service._UserProfileService;
import com.example.foodapp.auth.user.User;
//import com.example.foodapp.business.repo.BusinessRepo;
import com.example.foodapp.auth.user.UserProfiles.BusinessOwner;
import com.example.foodapp.product.model.Product;
import com.example.foodapp.product.model.ProductCategory;
import com.example.foodapp.product.model.Request.ProductCategoryRequest;
import com.example.foodapp.product.repo.ProductCategoryRepo;
import com.example.foodapp.product.serializers.ProductCategory_BusinessSerializer;
import com.example.foodapp.product.serializers.restaurant.RestaurantProductCategorySerializer;
import com.example.foodapp.product.serializers.restaurant.RestaurantProductSerializer;
import com.example.foodapp.product.service.business.OwnerProductCategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;


import java.security.Principal;
import java.util.List;

import static java.time.LocalDateTime.now;

@RequiredArgsConstructor
@Service
@Transactional
@Log4j2
public class OwnerProductCategoryServiceImplementation implements OwnerProductCategoryService {
    private final ProductCategoryRepo productCategoryRepo;
    private final UserRepository userRepo;
    private final _UserProfileService userProfileService;
    private final BusinessOwnerRepo businessOwnerRepo;
//    private final BusinessRepo businessRepo;

    @Override
    public ProductCategory create(ProductCategoryRequest payload, Principal principal) {

        String username = principal.getName();
        User user = userRepo.findByEmail(username).orElseThrow();

        String nameOfCategory = payload.getNameOfCategory();
        String descOfCategory = payload.getDescOfCategory();
        boolean isFeatured = payload.isFeatured();
        boolean isVisible = payload.isVisible();
        try
        {
            ProductCategory productCategory = new ProductCategory();
            productCategory.setNameOfCategory(nameOfCategory);
            productCategory.setDescOfCategory(descOfCategory);
            productCategory.setFeatured(isFeatured);
            productCategory.setCategoryVisible(isVisible);
            productCategory.setDateCreated(now());
            productCategory.setDateUpdated(now());
//            productCategory.setBusiness(userProfileService.returnBusinessOwnerProfile(userProfile).getBusiness());
//
            productCategoryRepo.save(productCategory);
            return productCategory;
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public String getMyList(Principal principal) throws Exception {
        String username = principal.getName();
        User user = userRepo.findByEmail(username).orElseThrow();
        BusinessOwner owner = businessOwnerRepo.findBusinessOwnerByUser(user)
                .orElseThrow(() -> new Exception("Business owner not found"));
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(new RestaurantProductCategorySerializer.ListSerializer());
        mapper.registerModule(module);
        return mapper.writeValueAsString(productCategoryRepo.findProductCategoriesByBusiness(owner.getBusiness()));
    }

    @Override
    public String get(Principal principal, Long id) throws Exception {
        User user = userRepo.findByEmail(principal.getName())
                .orElseThrow(() -> new Exception("User not found"));
        BusinessOwner owner = businessOwnerRepo.findBusinessOwnerByUser(user)
                .orElseThrow(() -> new Exception("Business owner not found"));
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(ProductCategory.class, new RestaurantProductCategorySerializer.DetailSerializer());
        mapper.registerModule(module);
        return mapper.writeValueAsString(productCategoryRepo
                .findProductCategoryByIdAndBusiness_BusinessOwner(id, owner)
                .orElseThrow(() -> new Exception("Not found")));
    }

    @Override
    public ProductCategory update(Long id) {
        return null;
    }

    @Override
    public Boolean delete(Long id, Principal principal) {
        if(principal != null)
        {
            try {
                String username = principal.getName();
                User user = userRepo.findByEmail(username).orElseThrow();
//                Business business = userProfile.getBusinessOwner().getBusiness();
//
//                productCategoryRepo.deleteProductCategoryById(id);
//                System.out.println(business);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("else");
        }

        return null;
    }
}
