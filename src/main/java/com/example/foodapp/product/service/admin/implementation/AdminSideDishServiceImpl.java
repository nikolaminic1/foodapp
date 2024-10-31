package com.example.foodapp.product.service.admin.implementation;

import com.example.foodapp.product.model.Product;
import com.example.foodapp.product.model.Request.SideDishRequest;
import com.example.foodapp.product.model.SideDish;
import com.example.foodapp.product.repo.ProductRepo;
import com.example.foodapp.product.repo.SideDishRepo;
import com.example.foodapp.product.service.admin.AdminSideDishService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.security.Principal;

@RequiredArgsConstructor
@Service
@Transactional
@Log4j2
public class AdminSideDishServiceImpl implements AdminSideDishService {
    private final SideDishRepo sideDishRepo;
    private final ProductRepo productRepo;

    @Override
    public String createAdmin(SideDishRequest sideDishRequest) throws Exception {
        Product product = productRepo.findProductById(sideDishRequest.getProductId())
                .orElseThrow(() -> new Exception("Product not found"));

        SideDish sideDish = new SideDish();
        sideDish.setNameOfSideDish(sideDishRequest.getNameOfSideDish());
        sideDish.setPrice(sideDishRequest.getPrice());
        sideDish.setDoesAffectPrice(sideDishRequest.getDoesAffectPrice());
        sideDish.setProduct(product);
        sideDishRepo.save(sideDish);
        return "OK";
    }

    @Override
    public String create(SideDishRequest sideDishRequest, Principal principal) throws Exception {
        return null;
    }

    @Override
    public String list(int page, int limit, Principal principal) throws Exception {
        return null;
    }

    @Override
    public String get(Long id) throws Exception {
        return null;
    }

    @Override
    public String update(Long id) throws Exception {
        return null;
    }

    @Override
    public Boolean delete(Long id) throws Exception {
        return null;
    }
}
