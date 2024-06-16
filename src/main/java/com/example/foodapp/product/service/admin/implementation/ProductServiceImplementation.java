package com.example.foodapp.product.service.admin.implementation;

import com.example.foodapp.product.model.Product;
import com.example.foodapp.product.model.Request.ProductRequest;
import com.example.foodapp.product.repo.ProductRepo;
import com.example.foodapp.product.serializers.admin.AdminProductSerializer;
import com.example.foodapp.product.service.admin.AdminProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Transactional
@Log4j2
public class ProductServiceImplementation implements AdminProductService {
    private final ProductRepo productRepo;

    @Override
    public Product create(ProductRequest productRequest, Principal principal) throws Exception {
        return null;
    }

    @Override
    public String list(int page, int limit, Principal principal) throws Exception{
        var products = productRepo.findAll(PageRequest.of(page, limit)).toList();
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(new AdminProductSerializer.ListSerializer());
        mapper.registerModule(module);
        return mapper.writeValueAsString(products);
    }

    @Override
    public String get(Long id) throws Exception {
        var product = productRepo.findById(id)
                .orElseThrow(() -> new Exception("Error"));
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Product.class, new AdminProductSerializer.DetailSerializer());
        mapper.registerModule(module);
        return mapper.writeValueAsString(product);
    }

    @Override
    public Product update(Long id) throws Exception {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new Exception("Not found"));
        return productRepo.save(product);
    }

    @Override
    public Boolean delete(Long id) throws Exception {
        productRepo.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public List<Map<String, Object>> getInitial(Long businessId, Principal principal) throws Exception {
        Map<String, Object> map = new HashMap<>();
//        map.put("")
        return null;
    }
}

