package com.example.foodapp.order.service.customer.implementation;


import com.example.foodapp.auth.repo.UserRepository;
import com.example.foodapp.auth.repo.profiles.CustomerRepository;
import com.example.foodapp.auth.service._UserProfileService;
import com.example.foodapp.auth.user.User;
import com.example.foodapp.auth.user.UserProfiles.Customer;
import com.example.foodapp.business.model.Business;
import com.example.foodapp.business.repo.BusinessRepo;
import com.example.foodapp.order.model.OrderO;
import com.example.foodapp.order.model.OrderProduct;
import com.example.foodapp.order.model.Request.AddAppendixRequest;
import com.example.foodapp.order.model.Request.AddSideDishToProductRequest;
import com.example.foodapp.order.repo.OrderProductRepo;
import com.example.foodapp.order.repo.OrderRepo;
import com.example.foodapp.order.serializer.customer.CustomerOrderProductSerializer;
import com.example.foodapp.order.service.customer.SideDishOrderProductCustomerService;
import com.example.foodapp.product.model.SideDish;
import com.example.foodapp.product.model.Product;
import com.example.foodapp.product.repo.SideDishRepo;
import com.example.foodapp.product.repo.ProductRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
public class SideDishOrderProductCustomerServiceImplementation implements SideDishOrderProductCustomerService {
    private final UserRepository userRepo;
    private final _UserProfileService userProfileService;
    private final OrderProductRepo orderProductRepo;
    private final OrderRepo orderRepo;
    private final SideDishRepo appendicesRepo;
    private final BusinessRepo businessRepo;
    private final ProductRepo productRepo;
    private final CustomerRepository customerRepo;

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
        SideDish sideDish = appendicesRepo.findById(appendixId).get();


    }

    @Override
    public SideDish get(Long id) {
        return null;
    }

    @Override
    public SideDish update(OrderProduct orderProduct, Principal principal) {
        return null;
    }

    @Override
    public Boolean delete(Long id, Principal principal) {
        return null;
    }

    @Override
    public String addSideDishToOrderProduct(
            Long orderProductId,
            AddSideDishToProductRequest request,
            Principal principal) throws Exception {
        OrderProduct orderProduct = orderProductRepo.findById(orderProductId)
                .orElseThrow(() -> new Exception("Order product not found"));

        User user = userRepo.findByEmail(principal.getName())
                .orElseThrow(() -> new Exception("User not found"));

        if (orderProduct.getOrderO().getCustomer().getUser() != user) {
            throw new Exception("This order product does not belong to this user");
        }

        SideDish sideDish = appendicesRepo.findById(request.getSideDishId())
                .orElseThrow(() -> new Exception("Side dish not found"));

        orderProductRepo.save(orderProduct);
        return this.orderProductMapping(orderProduct);
    }

    @Override
    public String initializeOrderProduct(Long id, Principal principal) throws Exception {
        Product product = productRepo.findProductById(id)
                .orElseThrow(() -> new Exception("Product not found"));

        Business business = product.getProductCategory().getBusiness();

        User user = userRepo.findByEmail(principal.getName())
                .orElseThrow(() -> new Exception("User not found"));

        Customer customer = customerRepo.findCustomerByUser(user)
                .orElseThrow(() -> new Exception("Customer not found"));

        OrderO orderO;
        if (orderRepo.findOrderOByCustomerAndOrderedAndBusiness(customer, false, business).isPresent()) {
            orderO = orderRepo.findOrderOByCustomerAndOrderedAndBusiness(customer, false, business).get();
        } else {
            orderO = new OrderO();
            orderO.setCustomer(customer);
            orderO.setStartTime(now());
            orderO.setOrdered(false);
            orderO.setPrepared(false);
            orderO.setPickedUp(false);
            orderO.setDelivered(false);
            orderO.setRefundRequested(false);
            orderO.setRefundGranted(false);
            orderO.updatePrice();
        }

        orderRepo.save(orderO);

        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setOrdered(false);
        orderProduct.setProduct(product);
        orderProduct.setTimeCreated(now());
        orderProduct.setQuantity(1);
        orderProduct.setPrice(product.getPriceOfProduct());
        orderProduct.setOrderO(orderO);
        orderProduct.setInOrder(false);

//        for (SideDishCategory category : product.getSideDishCategoryList()) {
//            SideDishCategoryOrderProduct categoryOrderProduct = new SideDishCategoryOrderProduct();
//            categoryOrderProduct.setSideDishCategory(category);
//            categoryOrderProduct.setOrderProduct(orderProduct);
//        }

        orderProductRepo.save(orderProduct);
        return this.orderProductMapping(orderProduct);
    }

    @Override
    public String orderProductMapping(OrderProduct orderProduct) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(OrderProduct.class, new CustomerOrderProductSerializer.DetailSerializer());
        mapper.registerModule(new JavaTimeModule());
        mapper.registerModule(module);
        return mapper.writeValueAsString(orderProduct);
    }
}
//
//        if(sideDish.getSideDishCategory().getProduct() != orderProduct.getProduct()){
//                throw new Exception("This appendix is not associated with this product");
//                }
//
//                List<SideDishCategoryOrderProduct> appendicesCategoryList = orderProduct.getSideDishCategoryList();
//
//        if(appendicesCategoryList.size() == 0){
//
//        SideDishCategoryOrderProduct appendicesCategoryOrderProduct = new SideDishCategoryOrderProduct();
////            appendicesCategoryOrderProduct.setSideDishCategory(appendices.getSideDishCategory());
//
//        if(appendicesCategoryOrderProduct.getSideDishList() == null) {
////                List<SideDish> appendicesList = new ArrayList<>();
////                appendicesList.add(appendices);
////                appendicesCategoryOrderProduct.setSideDishList(appendicesList);
//        }
//
////            appendicesCategoryOrderProduct.addSideDishToList(appendices);
//        appendicesCategoryOrderProduct.setOrderProducts(orderProduct);
//        orderProduct.updatePrice();
//
//        orderProductRepo.save(orderProduct);
//        appendicesCategoryOrderProductRepo.save(appendicesCategoryOrderProduct);
//
//        } else {
//
//        boolean doesSideDishCategoryOrderProductExists = appendicesCategoryOrderProductRepo
//        .findSideDishCategoryOrderProductBySideDishCategoryAndOrderProducts(sideDish.getSideDishCategory(), orderProduct)
//        .isPresent();
//
//        if(!doesSideDishCategoryOrderProductExists){
//        throw new Exception("This appendix does not have its own category");
//        }
//
//        SideDishCategoryOrderProduct appendicesCategoryOrderProduct = appendicesCategoryOrderProductRepo
//        .findSideDishCategoryOrderProductBySideDishCategoryAndOrderProducts(sideDish.getSideDishCategory(), orderProduct)
//        .get();
//
////            if(appendicesCategoryOrderProduct.getSideDishCategory().getNumberOfAllowed() <= appendicesCategoryOrderProduct.getSideDishList().size()){
////                throw new Exception("You can not add more appendices to order product");
////            }
//
////            if(appendicesCategoryOrderProduct.getSideDishList().contains(appendices)){
////                throw new Exception("You already have this appendix in your order product");
////            }
//
////            appendicesCategoryOrderProduct.addSideDishToList(appendices);
//        appendicesCategoryOrderProduct.setOrderProducts(orderProduct);
//        orderProduct.updatePrice();
//
//        orderProductRepo.save(orderProduct);
//        appendicesCategoryOrderProductRepo.save(appendicesCategoryOrderProduct);


//        SideDishCategory categoryOrderProduct = appendicesCategoryRepo
//                .findById(request.getSideDishOrderProductCategoryId())
//                .orElseThrow(() -> new Exception("Category not found"));
//
//
//                if (categoryOrderProduct.getSideDishList().size() <= sideDish.getSideDishCategory().getNumberOfAllowed()){
//                throw new Exception("Maximum number allowed");
//                }
//
//                List<SideDishCategoryOrderProduct> categoryOrderProductList = orderProduct.getSideDishCategoryList();
//
//        if (categoryOrderProductList != null){
//        for(SideDishCategoryOrderProduct op : categoryOrderProductList) {
//        if (op.getSideDishCategory() == categoryOrderProduct){
//        op.getSideDishList().add(sideDish);
//        appendicesCategoryOrderProductRepo.save(op);
//        } else {
//        SideDishCategoryOrderProduct opc = new SideDishCategoryOrderProduct();
//        opc.setOrderProduct(orderProduct);
//        opc.setSideDishCategory(categoryOrderProduct);
//
//        List<SideDish> sideDishList = new ArrayList<>();
//        sideDishList.add(sideDish);
//        opc.setSideDishList(sideDishList);
//        appendicesCategoryOrderProductRepo.save(opc);
//
//        }
//        }
//        }