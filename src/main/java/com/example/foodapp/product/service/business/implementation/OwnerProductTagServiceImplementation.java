package com.example.foodapp.product.service.business.implementation;

import com.example.foodapp.auth.repo.UserRepository;
import com.example.foodapp.auth.user.UserProfile;
import com.example.foodapp.product.model.Product;
import com.example.foodapp.product.model.ProductTag;
import com.example.foodapp.product.model.Request.ProductTagRequest;
import com.example.foodapp.product.repo.ProductRepo;
import com.example.foodapp.product.repo.ProductTagRepo;
import com.example.foodapp.product.service.business.OwnerProductTagService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Transactional
@Log4j2
public class OwnerProductTagServiceImplementation implements OwnerProductTagService {
    private final ProductTagRepo productTagRepo;
    private final UserRepository userRepo;
    private final ProductRepo productRepo;

    @Override
    public List<ProductTag> create(ProductTagRequest productTagRequest, Principal principal) {
        Long productId = productTagRequest.getProductId();
        List<String> listMap = productTagRequest.getTagList();

        if(productRepo.findById(productId).isPresent()){
            Product product = productRepo.findById(productId).get();
            UserProfile userProfile = userRepo.findByEmail(principal.getName()).orElseThrow().getUserProfile();

//            if(userProfile != product.getProductCategory().getBusiness().getBusinessOwner().getUserProfile()) {
//                return null;
//            }

            for (String productTag : listMap) {
//                if(productTagRepo.existsByNameAndProduct(productTag.toUpperCase(), product)){
//                    continue;
//                }

                ProductTag productTagObject = new ProductTag();
                productTagObject.setName(productTag.toUpperCase());
//                productTagObject.setProduct(product);
                productTagRepo.save(productTagObject);
            }
//            return productTagRepo.findProductTagsByProduct(product);
        }
        return null;
    }

    @Override
    public Collection<ProductTag> list(int limit) {
        return null;
    }

    @Override
    public ProductTag get(Long id) {
        return null;
    }

    @Override
    public ProductTag update(Long id) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
