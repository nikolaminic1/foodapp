package com.example.foodapp.business.service.admin_service;


import com.example.foodapp.product.model.Image;

import java.util.Collection;

public interface AdminImageService {
    Image create(Image image);
    Collection<Image> list(int limit);
    Image get(Long id);
    Image update(Long id);
    Boolean delete(Long id);
}
