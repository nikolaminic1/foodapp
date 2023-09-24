package com.example.foodapp.order.service.noAuthCustomer.implementation;


import com.example.foodapp.order.model.OrderProduct;
import com.example.foodapp.order.model.Request.OrderProductRequest;
import com.example.foodapp.order.service.noAuthCustomer.NoAuthCustomerOrderProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
@Transactional
@Log4j2
public class NoAuthCustomerOrderProductServiceImplementation implements NoAuthCustomerOrderProductService {

    @Override
    public OrderProduct create(OrderProductRequest orderProductRequest) {
        return null;
    }

    @Override
    public OrderProduct get(Long id) {
        return null;
    }

    @Override
    public OrderProduct update(Long id) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
