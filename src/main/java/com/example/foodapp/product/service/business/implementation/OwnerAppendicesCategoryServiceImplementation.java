package com.example.foodapp.product.service.business.implementation;

import com.example.foodapp.auth.repo.UserRepository;
import com.example.foodapp.auth.service._UserProfileService;
import com.example.foodapp.auth.user.User;
import com.example.foodapp.product.model.Appendices;
import com.example.foodapp.product.model.AppendicesCategory;
import com.example.foodapp.product.model.Product;
import com.example.foodapp.product.model.Request.AppendicesCategoryCreateRequest;
import com.example.foodapp.product.repo.AppendicesCategoryRepo;
import com.example.foodapp.product.repo.AppendicesRepo;
import com.example.foodapp.product.repo.ProductRepo;
import com.example.foodapp.product.service.business.OwnerAppendicesCategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
@Log4j2
public class OwnerAppendicesCategoryServiceImplementation implements OwnerAppendicesCategoryService {
    private final UserRepository userRepo;
    private final _UserProfileService userProfileService;
    private final AppendicesRepo appendicesRepo;
    private final AppendicesCategoryRepo appendicesCategoryRepo;
    private final ProductRepo productRepo;

    @Override
    public AppendicesCategory create(AppendicesCategoryCreateRequest appendicesCategoryCreateRequest, Principal principal) throws Exception {
        String nameOfCategory = appendicesCategoryCreateRequest.getNameOfCategory();
        Boolean isRequired = appendicesCategoryCreateRequest.getIsRequired();
        Long productId = appendicesCategoryCreateRequest.getProductId();
        int numberOfAllowed = appendicesCategoryCreateRequest.getNumberOfAllowed();

        if(nameOfCategory == null){
            throw new Exception("Name of category should be defined");
        }

        if(isRequired == null){
            throw new Exception("Name of category should be defined");
        }

        if(productId == null){
            throw new Exception("Name of category should be defined");
        }

        AppendicesCategory appendicesCategory = new AppendicesCategory();
        appendicesCategory.setIsRequired(isRequired);
        appendicesCategory.setNameOfCategory(nameOfCategory);
        appendicesCategory.setNumberOfAllowed(numberOfAllowed);

        if(productRepo.findById(productId).isPresent()){
            User user = userRepo.findByEmail(principal.getName()).orElseThrow();
            Product product = productRepo.findById(productId).get();

//            if(userProfile.getProfile().getRole() == ERole.ROLE_BUSINESS){
//                if(product.getProductCategory().getBusiness() == userProfileService.returnBusinessOwnerProfile(userProfile).getBusiness()){
//                    appendicesCategory.setProduct(product);
//                    appendicesCategoryRepo.save(appendicesCategory);
//                    return appendicesCategory;
//                } else {
//                    throw new Exception("This product is not in your restaurant");
//                }
//            } else {
//                throw new Exception("You are not business owner");
//            }
            return null;

        } else {
            throw new Exception("Product doesn't exist");
        }
    }

    @Override
    public AppendicesCategory get(Long id) {
        return null;
    }

    @Override
    public List<AppendicesCategory> getAllAppendicesCategory(Principal principal) {
        return null;
    }

    @Override
    public List<AppendicesCategory> getAppendicesCategoryByProduct(Product product, Principal principal) {
        return null;
    }

    @Override
    public AppendicesCategory update(Long id) {
        return null;
    }

    @Override
    public Boolean delete(Long id, Principal principal) {
        return null;
    }

    @Override
    public void createDummyData() {
        Product product = productRepo.findProductById(202L).get();
        List<AppendicesCategory> appendicesCategoryList = appendicesCategoryRepo.findAppendicesCategoriesByProduct(product);
        System.out.println(appendicesCategoryList);
        appendicesCategoryList.forEach((app) -> {
            Appendices appendices = new Appendices();
            appendices.setAppendicesCategory(app);
            appendices.setNameOfAppendices("name-2");
            appendices.setDoesAffectPrice(true);
            appendices.setPrice(80);
            appendicesRepo.save(appendices);
            appendicesCategoryRepo.save(app);
        });
    }
}
