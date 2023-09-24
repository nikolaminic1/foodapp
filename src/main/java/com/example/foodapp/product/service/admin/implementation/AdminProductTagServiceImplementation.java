package com.example.foodapp.product.service.admin.implementation;

import com.example.foodapp.product.model.ProductTag;
import com.example.foodapp.product.repo.ProductTagRepo;
import com.example.foodapp.product.service.admin.AdminProductTagService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;

@RequiredArgsConstructor
@Service
@Transactional
@Log4j2
public class AdminProductTagServiceImplementation implements AdminProductTagService {
    private final ProductTagRepo productTagRepo;

//    @Override
//    public ProductTag create(ProductTag productTag) {
//        return productTagRepo.save(productTag);
//    }
//
//    @Override
//    public Collection<ProductTag> list(int limit) {
//        return productTagRepo.findAll(PageRequest.of(0, limit)).toList();
//    }
//
//    @Override
//    public ProductTag get(Long id) {
//        return productTagRepo.findById(id).get();
//    }
//
//    @Override
//    public ProductTag update(Long id) {
//        ProductTag productTag = productTagRepo.findById(id).get();
//        return productTagRepo.save(productTag);    }
//
//    @Override
//    public Boolean delete(Long id) {
//        productTagRepo.deleteById(id);
//        return Boolean.TRUE;
//    }

    @Override
    public ProductTag create(ProductTag business) {
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
