package com.example.foodapp.product.service.business;

import com.example.foodapp.product.model.ProductImage;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

public interface BusinessImageService {
    ProductImage create(MultipartFile file, Long id, Principal principal);
    ProductImage get(Long id);
    List<ProductImage> getMyList(Principal principal);
    ProductImage update(Long id);
    Boolean delete(Long id, Principal principal);
}
