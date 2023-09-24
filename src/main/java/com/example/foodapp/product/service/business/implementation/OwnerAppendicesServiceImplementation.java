package com.example.foodapp.product.service.business.implementation;


import com.example.foodapp.auth.repo.UserRepository;
import com.example.foodapp.auth.service._UserProfileService;
import com.example.foodapp.auth.user.User;
import com.example.foodapp.product.model.Appendices;
import com.example.foodapp.product.model.AppendicesCategory;
import com.example.foodapp.product.model.Product;
import com.example.foodapp.product.model.Request.AppendicesCreateRequest;
import com.example.foodapp.product.repo.AppendicesCategoryRepo;
import com.example.foodapp.product.repo.AppendicesRepo;
import com.example.foodapp.product.repo.ProductRepo;
import com.example.foodapp.product.service.business.OwnerAppendicesService;
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
public class OwnerAppendicesServiceImplementation implements OwnerAppendicesService {
    private final UserRepository userRepo;
    private final _UserProfileService userProfileService;
    private final AppendicesRepo appendicesRepo;
    private final AppendicesCategoryRepo appendicesCategoryRepo;
    private final ProductRepo productRepo;

    @Override
    public Appendices create(@RequestBody AppendicesCreateRequest appendicesCreateRequest, Principal principal) throws Exception{
        String nameOfAppendices = appendicesCreateRequest.getNameOfAppendices();
        Boolean doesAffectPrice = appendicesCreateRequest.getDoesAffectPrice();
        Long appendicesCategoryId = appendicesCreateRequest.getAppendicesCategoryId();
        double price = appendicesCreateRequest.getPrice();

        if(nameOfAppendices == null){
            throw new Exception("Name should be defined");
        }

        if(doesAffectPrice == null){
            throw new Exception("Does affect price should be defined");
        }

        if(appendicesCategoryId == null){
            throw new Exception("Category need to be defined");
        }

        User user = userRepo.findByEmail(principal.getName()).orElseThrow();
        if(appendicesCategoryRepo.findById(appendicesCategoryId).isPresent()){
            AppendicesCategory appendicesCategory = appendicesCategoryRepo.findById(appendicesCategoryId).get();
//            if(appendicesCategory.getProduct().getProductCategory().getBusiness() == userProfileService
//                    .returnBusinessOwnerProfile(user.getUserProfile()).getBusiness()){
//                Appendices appendices = new Appendices();
//                appendices.setAppendicesCategory(appendicesCategory);
//                appendices.setDoesAffectPrice(doesAffectPrice);
//                appendices.setNameOfAppendices(nameOfAppendices);
//                appendices.setPrice(price);
//                appendicesRepo.save(appendices);
//                return appendices;
//            } else {
//                throw new Exception("This appendices category is not for your product");
//            }
            return null;
        } else {
            throw new Exception("This category does't exist");
        }
    }

    @Override
    public Appendices get(Long id) {
        return null;
    }

    @Override
    public List<Appendices> getAllAppendices(Principal principal) {
        return null;
    }

    @Override
    public List<Appendices> getAppendicesByCategory(AppendicesCategory appendicesCategory, Principal principal) {
        return null;
    }

    @Override
    public List<Appendices> getAppendicesByProduct(Product product, Principal principal) {
        return null;
    }

    @Override
    public Appendices update(Long id) {
        return null;
    }

    @Override
    public Boolean delete(Long id, Principal principal) {
        return null;
    }
}
