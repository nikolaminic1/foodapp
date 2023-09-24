package com.example.foodapp.order.service.customer.implementation;


import com.example.foodapp.auth.repo.UserRepository;
import com.example.foodapp.order.model.AppendicesCategoryOrderProduct;
import com.example.foodapp.order.model.OrderProduct;
import com.example.foodapp.order.repo.OrderProductRepo;
import com.example.foodapp.order.repo.OrderRepo;
import com.example.foodapp.order.service.customer.AppendicesCategoryOrderProductService;
import com.example.foodapp.product.repo.AppendicesCategoryRepo;
import com.example.foodapp.product.repo.AppendicesRepo;
import com.example.foodapp.product.repo.ProductRepo;
import com.example.foodapp.product.repo.ProductVariationRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.security.Principal;

@RequiredArgsConstructor
@Service
@Transactional
@Log4j2
public class AppendicesCategoryOrderProductServiceImplementation implements AppendicesCategoryOrderProductService {
    private final UserRepository userRepo;
    private final OrderProductRepo orderProductRepo;
    private final OrderRepo orderRepo;
    private final ProductVariationRepo productVariationRepo;
    private final AppendicesCategoryRepo appendicesCategoryRepo;
    private final AppendicesRepo appendicesRepo;
    private final ProductRepo productRepo;

    @Override
    public AppendicesCategoryOrderProduct create(OrderProduct orderProduct, Principal principal) {
        return null;
    }

    @Override
    public AppendicesCategoryOrderProduct get(Long id) {
        return null;
    }

    @Override
    public AppendicesCategoryOrderProduct update(OrderProduct orderProduct, Principal principal) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
