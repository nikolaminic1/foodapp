package com.example.foodapp.product.service.business.implementation;

import com.example.foodapp.auth.repo.UserRepository;
import com.example.foodapp.auth.repo.profiles.BusinessOwnerProfileRepository;
import com.example.foodapp.auth.repo.profiles.UserProfileRepo;
import com.example.foodapp.auth.service._UserProfileService;
import com.example.foodapp.auth.user.User;
import com.example.foodapp.auth.user.UserProfile;
//import com.example.foodapp.business.repo.BusinessRepo;
import com.example.foodapp.product.model.ProductCategory;
import com.example.foodapp.product.model.Request.ProductCategoryRequest;
import com.example.foodapp.product.repo.ProductCategoryRepo;
import com.example.foodapp.product.service.business.OwnerProductCategoryService;
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
    private final UserProfileRepo userProfileRepo;
    private final BusinessOwnerProfileRepository businessOwnerRepo;
//    private final BusinessRepo businessRepo;

    @Override
    public ProductCategory create(ProductCategoryRequest payload, Principal principal) {

        String username = principal.getName();
        User user = userRepo.findByEmail(username).orElseThrow();
        UserProfile userProfile = user.getUserProfile();

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
    public List<ProductCategory> getMyList(Principal principal) {
        log.error("error");

        if(principal != null)
        {
            try {
                String username = principal.getName();
                User user = userRepo.findByEmail(username).orElseThrow();
                return null;
//                return productCategoryRepo.findProductCategoriesByBusiness(
//                        userProfileService.returnBusinessOwnerProfile(user.getUserProfile()).getBusiness());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("else");
        }

        return null;
    }

    @Override
    public ProductCategory get(Long id) {
        return null;
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
                UserProfile userProfile = user.getUserProfile();
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
