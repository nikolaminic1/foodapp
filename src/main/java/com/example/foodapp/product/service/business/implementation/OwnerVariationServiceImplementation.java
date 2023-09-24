package com.example.foodapp.product.service.business.implementation;


import com.example.foodapp.auth.repo.UserRepository;
import com.example.foodapp.auth.service._UserProfileService;
import com.example.foodapp.auth.user.User;
import com.example.foodapp.product.model.Product;
import com.example.foodapp.product.model.ProductCategory;
import com.example.foodapp.product.model.Request.VariationRequest;
import com.example.foodapp.product.model.Variation;
import com.example.foodapp.product.repo.ProductRepo;
import com.example.foodapp.product.repo.VariationRepo;
import com.example.foodapp.product.service.business.OwnerVariationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
@Log4j2
public class OwnerVariationServiceImplementation implements OwnerVariationService {
    private final UserRepository userRepo;
    private final ProductRepo productRepo;
    private final VariationRepo variationRepo;
    private final _UserProfileService userProfileService;

    @Override
    public Variation create(VariationRequest variationRequest, Principal principal) {
        Long productId = variationRequest.getProductId();
        String variationName = variationRequest.getName();

        User user = userRepo.findByEmail(principal.getName()).orElseThrow();

//        List<ProductCategory> productCategoryList = userProfileService
//                .returnBusinessOwnerProfile(
//                        userRepo.findByEmail(principal.getName())
//                                .getUserProfile()).getBusiness().getProductCategories();
//
//        if(productRepo.findById(productId).isPresent()){
//            Product product = productRepo.findById(productId).get();
//            if(productCategoryList.contains(product.getProductCategory())){
//                boolean doesVariationExists = variationRepo.existsVariationByNameAndProduct(variationName, product);
//                if (doesVariationExists){
//                    return null;
//                }
//                Variation variation = new Variation();
//                variation.setName(variationName);
//                variation.setProduct(product);
//                variationRepo.save(variation);
//                return variation;
//            }
//
//        }
        return null;
    }

    @Override
    public Collection<Variation> list(int limit) {
        return null;
    }

    @Override
    public Variation get(Long id) {
        return null;
    }

    @Override
    public Variation update(Long id) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
