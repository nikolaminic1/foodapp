package com.example.foodapp.product.service.cutomer.implementation;

import com.example.foodapp.product.model.Product;
import com.example.foodapp.product.repo.ProductRepo;
import com.example.foodapp.product.serializers.restaurant.RestaurantProductSerializer;
import com.example.foodapp.product.service.cutomer.CustomerProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.security.Principal;

@RequiredArgsConstructor
@Service
@Transactional
@Log4j2
public class CustomerProductServiceImplementation implements CustomerProductService {
    private final ProductRepo productRepo;

    @Override
    public String get(Long id, Principal principal) throws Exception {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new Exception("Product not found."));
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Product.class, new RestaurantProductSerializer.DetailSerializer());
        mapper.registerModule(module);
        return mapper.writeValueAsString(product);
    }
}
