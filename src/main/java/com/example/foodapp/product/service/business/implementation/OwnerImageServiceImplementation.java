package com.example.foodapp.product.service.business.implementation;

import com.example.foodapp.api_resources.ImageFileSaveService;
import com.example.foodapp.auth.repo.UserRepository;
import com.example.foodapp.auth.service._UserProfileService;
import com.example.foodapp.auth.user.User;
//import com.example.foodapp.business.model.Business;
import com.example.foodapp.product.model.ProductImage;
import com.example.foodapp.product.repo.ProductImageRepo;
import com.example.foodapp.product.repo.ProductRepo;
import com.example.foodapp.product.service.business.BusinessImageService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;


@RequiredArgsConstructor
@Service
@Transactional
@Log4j2
public class OwnerImageServiceImplementation implements BusinessImageService {
    private final ProductImageRepo productImageRepo;
    private final ProductRepo productRepo;
    private final UserRepository userRepo;
    private final _UserProfileService userProfileService;

    @Override
    public ProductImage create(MultipartFile file, Long productId, Principal principal) {
        if (file != null){
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            long size = file.getSize();

            String fileCode = null;

            try {
                User user = userRepo.findByEmail(principal.getName()).orElseThrow();
                fileCode = ImageFileSaveService.saveFile(fileName, file);
                String pathToSave = "/general/product/image/" + fileCode;
                System.out.println(pathToSave);
                ProductImage image = new ProductImage();
                image.setImageUrl(pathToSave);
                image.setNameOfImage(fileName);
                productImageRepo.save(image);

                if(productRepo.findById(productId).isPresent()){
//                    Business business =
//                            userProfileService.returnBusinessOwnerProfile(user).getBusiness();
//                    List<ProductCategory> productCategoryList = business.getProductCategories();
//                    Product product = productRepo.findById(productId).get();

//                    if(productCategoryList.contains(product.getProductCategory())){
//
//                        if(product.getProductImage() == null){
//                            product.setProductImage(image);
//                        } else {
//                            productImageRepo.delete(product.getProductImage());
//                            product.setProductImage(image);
//                        }
//                        productRepo.save(product);
//
//                    }
                    return image;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Override
    public ProductImage get(Long id) {
        return null;
    }

    @Override
    public List<ProductImage> getMyList(Principal principal) {
        return null;
    }

    @Override
    public ProductImage update(Long id) {
        return null;
    }

    @Override
    public Boolean delete(Long id, Principal principal) {
        return null;
    }
}
