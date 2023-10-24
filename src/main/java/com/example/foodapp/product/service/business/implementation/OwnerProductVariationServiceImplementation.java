package com.example.foodapp.product.service.business.implementation;


import com.example.foodapp.auth.repo.UserRepository;
import com.example.foodapp.auth.service._UserProfileService;
import com.example.foodapp.product.model.ProductCategory;
import com.example.foodapp.product.model.ProductVariation;
import com.example.foodapp.product.model.Request.ProductVariationRequest;
import com.example.foodapp.product.model.Variation;
import com.example.foodapp.product.repo.ProductRepo;
import com.example.foodapp.product.repo.ProductVariationRepo;
import com.example.foodapp.product.repo.VariationRepo;
import com.example.foodapp.product.service.business.OwnerProductVariationService;
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
public class OwnerProductVariationServiceImplementation implements OwnerProductVariationService {
    private final VariationRepo variationRepo;
    private final ProductVariationRepo productVariationRepo;
    private final UserRepository userRepo;
    private final _UserProfileService userProfileService;
    private final ProductRepo productRepo;

    @Override
    public ProductVariation create(ProductVariationRequest productVariationRequest, Principal principal) {
        if(variationRepo.findById(productVariationRequest.getVariationId()).isPresent()){
//            List<ProductCategory> productCategoryList = userProfileService
//                    .returnBusinessOwnerProfile(
//                            userRepo.findByEmail(principal.getName())
//                                    ).getBusiness().getProductCategories();
//            Variation variation = variationRepo.findById(productVariationRequest.getVariationId()).get();
//
//            if(productCategoryList.contains(variation.getProduct().getProductCategory())){
//                ProductVariation productVariation = new ProductVariation();
//
//                productVariation.setCodeOfVariation(productVariationRequest.getCodeOfVariation());
//                productVariation.setDoesAffectPrice(productVariationRequest.getDoesAffectPrice());
//                productVariation.setIsOnDiscount(productVariationRequest.getOnDiscount());
//                productVariation.setName(productVariationRequest.getName());
//                productVariation.setPriceOfVariation(productVariationRequest.getPriceOfVariation());
//                productVariation.setPriceOfVariationDiscount(productVariationRequest.getPriceOfVariationDiscount());
//                productVariation.setValue(productVariationRequest.getValue());
//                productVariation.setVariation(variation);
//                variation.getProductVariationList().add(productVariation);
//                variationRepo.save(variation);
//                productVariationRepo.save(productVariation);
//                return productVariation;
//            }
        }
        return null;
    }

    @Override
    public Collection<ProductVariation> list(int limit) {
        return null;
    }

    @Override
    public ProductVariation get(Long id) {
        return null;
    }

    @Override
    public ProductVariation update(Long id) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
