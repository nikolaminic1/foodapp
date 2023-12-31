package com.example.foodapp.order.service.customer.implementation;


import com.example.foodapp.auth.repo.UserRepository;
import com.example.foodapp.auth.service._UserProfileService;
import com.example.foodapp.auth.user.User;
import com.example.foodapp.auth.user.UserProfiles.Customer;
import com.example.foodapp.order.model.AppendicesCategoryOrderProduct;
import com.example.foodapp.order.model.OrderO;
import com.example.foodapp.order.model.OrderProduct;
import com.example.foodapp.order.model.Request.AddAppendixRequest;
import com.example.foodapp.order.repo.AppendicesCategoryOrderProductRepo;
import com.example.foodapp.order.repo.OrderProductRepo;
import com.example.foodapp.order.repo.OrderRepo;
import com.example.foodapp.order.service.customer.AppendicesOrderProductCustomerService;
import com.example.foodapp.product.model.Appendices;
import com.example.foodapp.product.repo.AppendicesCategoryRepo;
import com.example.foodapp.product.repo.AppendicesRepo;
import com.example.foodapp.product.repo.ProductRepo;
import com.example.foodapp.product.repo.ProductVariationRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDateTime.now;

@RequiredArgsConstructor
@Service
@Transactional
@Log4j2
public class AppendicesOrderProductCustomerServiceImplementation implements AppendicesOrderProductCustomerService {
    private final UserRepository userRepo;
    private final _UserProfileService userProfileService;
    private final OrderProductRepo orderProductRepo;
    private final OrderRepo orderRepo;
    private final ProductVariationRepo productVariationRepo;
    private final AppendicesCategoryRepo appendicesCategoryRepo;
    private final AppendicesCategoryOrderProductRepo appendicesCategoryOrderProductRepo;
    private final AppendicesRepo appendicesRepo;
    private final ProductRepo productRepo;

    @Override
    public void addToOrderProduct(AddAppendixRequest addAppendixRequest, Principal principal) throws Exception {
        User user = userRepo.findByEmail(principal.getName()).orElseThrow();
        Long appendixId = addAppendixRequest.getAppendixId();
        Long orderProductId = addAppendixRequest.getOrderProductId();
        Customer customer = userProfileService.returnCustomer(user);

        boolean doesAppendixExists = appendicesRepo.findById(appendixId).isPresent();
        boolean doesOrderProductExists = orderProductRepo.findById(orderProductId).isPresent();
        boolean doesOrderExists = orderRepo.findOrderOByCustomerAndOrdered(customer, false).isPresent();
        OrderO orderO = orderRepo.findOrderOByCustomerAndOrdered(customer, false).get();
        orderO.setTimeUpdated(now());
        orderRepo.save(orderO);

        if(!doesOrderExists){
            throw new Exception("You dont have active order");
        }

        if(!doesOrderProductExists){
            throw new Exception("This order product does not exists");
        }

        if(!doesAppendixExists){
            throw new Exception("This appendix does not exists");
        }

        OrderProduct orderProduct = orderProductRepo.findById(orderProductId).get();
        Appendices appendices = appendicesRepo.findById(appendixId).get();

        if(appendices.getAppendicesCategory().getProduct() != orderProduct.getProduct()){
            throw new Exception("This appendix is not associated with this product");
        }

        List<AppendicesCategoryOrderProduct> appendicesCategoryList = orderProduct.getAppendicesCategoryList();

        if(appendicesCategoryList.size() == 0){

            AppendicesCategoryOrderProduct appendicesCategoryOrderProduct = new AppendicesCategoryOrderProduct();
//            appendicesCategoryOrderProduct.setAppendicesCategory(appendices.getAppendicesCategory());

            if(appendicesCategoryOrderProduct.getAppendicesList() == null) {
//                List<Appendices> appendicesList = new ArrayList<>();
//                appendicesList.add(appendices);
//                appendicesCategoryOrderProduct.setAppendicesList(appendicesList);
            }

//            appendicesCategoryOrderProduct.addAppendicesToList(appendices);
            appendicesCategoryOrderProduct.setOrderProducts(orderProduct);
            orderProduct.updatePrice();

            orderProductRepo.save(orderProduct);
            appendicesCategoryOrderProductRepo.save(appendicesCategoryOrderProduct);

        } else {

            boolean doesAppendicesCategoryOrderProductExists = appendicesCategoryOrderProductRepo
                    .findAppendicesCategoryOrderProductByAppendicesCategoryAndOrderProducts(appendices.getAppendicesCategory(), orderProduct)
                    .isPresent();

            if(!doesAppendicesCategoryOrderProductExists){
                throw new Exception("This appendix does not have its own category");
            }

            AppendicesCategoryOrderProduct appendicesCategoryOrderProduct = appendicesCategoryOrderProductRepo
                    .findAppendicesCategoryOrderProductByAppendicesCategoryAndOrderProducts(appendices.getAppendicesCategory(), orderProduct)
                    .get();

//            if(appendicesCategoryOrderProduct.getAppendicesCategory().getNumberOfAllowed() <= appendicesCategoryOrderProduct.getAppendicesList().size()){
//                throw new Exception("You can not add more appendices to order product");
//            }

//            if(appendicesCategoryOrderProduct.getAppendicesList().contains(appendices)){
//                throw new Exception("You already have this appendix in your order product");
//            }

//            appendicesCategoryOrderProduct.addAppendicesToList(appendices);
            appendicesCategoryOrderProduct.setOrderProducts(orderProduct);
            orderProduct.updatePrice();

            orderProductRepo.save(orderProduct);
            appendicesCategoryOrderProductRepo.save(appendicesCategoryOrderProduct);
        }
    }

    @Override
    public Appendices get(Long id) {
        return null;
    }

    @Override
    public Appendices update(OrderProduct orderProduct, Principal principal) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
