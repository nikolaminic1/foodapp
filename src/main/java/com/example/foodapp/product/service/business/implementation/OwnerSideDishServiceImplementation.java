package com.example.foodapp.product.service.business.implementation;


import com.example.foodapp.auth.repo.UserRepository;
import com.example.foodapp.auth.service._UserProfileService;
import com.example.foodapp.auth.user.User;
import com.example.foodapp.product.model.SideDish;
import com.example.foodapp.product.model.Product;
import com.example.foodapp.product.model.Request.SideDishCreateRequest;
import com.example.foodapp.product.repo.SideDishRepo;
import com.example.foodapp.product.repo.ProductRepo;
import com.example.foodapp.product.service.business.OwnerSideDishService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
@Log4j2
public class OwnerSideDishServiceImplementation implements OwnerSideDishService {
    private final UserRepository userRepo;
    private final _UserProfileService userProfileService;
    private final SideDishRepo appendicesRepo;
    private final ProductRepo productRepo;

    @Override
    public SideDish create(@RequestBody SideDishCreateRequest appendicesCreateRequest, Principal principal) throws Exception{
        String nameOfSideDish = appendicesCreateRequest.getNameOfSideDish();
        Boolean doesAffectPrice = appendicesCreateRequest.getDoesAffectPrice();
        double price = appendicesCreateRequest.getPrice();

        if(nameOfSideDish == null){
            throw new Exception("Name should be defined");
        }

        if(doesAffectPrice == null){
            throw new Exception("Does affect price should be defined");
        }

//        if(appendicesCategoryId == null){
//            throw new Exception("Category need to be defined");
//        }

        User user = userRepo.findByEmail(principal.getName()).orElseThrow();
//        if(appendicesCategoryRepo.findById(appendicesCategoryId).isPresent()){
//            SideDishCategory appendicesCategory = appendicesCategoryRepo.findById(appendicesCategoryId).get();
//            if(appendicesCategory.getProduct().getProductCategory().getBusiness() == userProfileService
//                    .returnBusinessOwnerProfile(user).getBusiness()){
//                SideDish appendices = new SideDish();
//                appendices.setSideDishCategory(appendicesCategory);
//                appendices.setDoesAffectPrice(doesAffectPrice);
//                appendices.setNameOfSideDish(nameOfSideDish);
//                appendices.setPrice(price);
//                appendicesRepo.save(appendices);
//                return appendices;
//            } else {
//                throw new Exception("This appendices category is not for your product");
//            }
//            return null;
//        } else {
//            throw new Exception("This category does't exist");
//        }
        return null;
    }

    @Override
    public SideDish get(Long id) {
        return null;
    }

    @Override
    public List<SideDish> getAllSideDish(Principal principal) {
        return null;
    }


    @Override
    public List<SideDish> getSideDishByProduct(Product product, Principal principal) {
        return null;
    }

    @Override
    public SideDish update(Long id) {
        return null;
    }

    @Override
    public Boolean delete(Long id, Principal principal) {
        return null;
    }
}
