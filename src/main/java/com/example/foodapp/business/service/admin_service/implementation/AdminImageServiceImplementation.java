package com.example.foodapp.business.service.admin_service.implementation;

import com.example.foodapp.business.service.admin_service.AdminImageService;
import com.example.foodapp.product.model.Image;
import com.example.foodapp.product.repo.ImageRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.Collection;

import static java.lang.Boolean.TRUE;
import static org.springframework.data.domain.PageRequest.of;

@RequiredArgsConstructor
@Service
@Transactional
@Log4j2
public class AdminImageServiceImplementation implements AdminImageService {
    private final ImageRepo imageRepo;

    @Override
    public Image create(Image image) {
        return imageRepo.save(image);
    }

    @Override
    public Collection<Image> list(int limit) {
        return imageRepo.findAll(of(0, limit)).toList();
    }

    @Override
    public Image get(Long id) {
        return imageRepo.findById(id).get();
    }

    @Override
    public Image update(Long id) {
        Image image = imageRepo.findById(id).get();
        return imageRepo.save(image);
    }

    @Override
    public Boolean delete(Long id) {
        imageRepo.deleteById(id);
        return TRUE;
    }
}
