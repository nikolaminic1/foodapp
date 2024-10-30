package com.example.foodapp.order.service.customer.implementation;

import com.example.foodapp.auth.repo.UserRepository;
import com.example.foodapp.auth.service._UserProfileService;
import com.example.foodapp.auth.user.User;
import com.example.foodapp.auth.user.UserProfiles.Customer;
import com.example.foodapp.order.model.AppendicesCategoryOrderProduct;
import com.example.foodapp.order.model.OrderO;
import com.example.foodapp.order.model.OrderProduct;
import com.example.foodapp.order.model.Request.OrderProductRequest;
import com.example.foodapp.order.model.Request.OrderProductUpdateRequest;
import com.example.foodapp.order.repo.OrderProductRepo;
import com.example.foodapp.order.repo.OrderRepo;
import com.example.foodapp.order.service.customer.CustomerOrderProductService;
import com.example.foodapp.product.model.Appendices;
import com.example.foodapp.product.model.AppendicesCategory;
import com.example.foodapp.product.model.Product;
import com.example.foodapp.product.model.ProductVariation;
import com.example.foodapp.product.repo.AppendicesCategoryRepo;
import com.example.foodapp.product.repo.AppendicesRepo;
import com.example.foodapp.product.repo.ProductRepo;
import com.example.foodapp.product.repo.ProductVariationRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Order;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static java.lang.System.currentTimeMillis;
import static java.time.LocalDateTime.now;

@RequiredArgsConstructor
@Service
@Transactional
@Log4j2
public class CustomerOrderProductServiceImplementation implements CustomerOrderProductService {
    private final UserRepository userRepo;
    private final _UserProfileService userProfileService;
    private final OrderProductRepo orderProductRepo;
    private final OrderRepo orderRepo;
    private final ProductVariationRepo productVariationRepo;
    private final AppendicesCategoryRepo appendicesCategoryRepo;
    private final AppendicesRepo appendicesRepo;
    private final ProductRepo productRepo;

    @Override
    public OrderProduct create(OrderProductRequest orderProductRequest, Principal principal) throws Exception {
        User user = userRepo.findByEmail(principal.getName()).orElseThrow();
        Customer customer = userProfileService.returnCustomer(user);
        boolean doesOrderExits = orderRepo.findOrderOByCustomerAndOrdered(customer, false).isPresent();
        long orderProductVariation = orderProductRequest.getProductVariationId();
        boolean doesProductExits = productRepo.findById(orderProductRequest.getProductId()).isPresent();

        // need to update order product price and order price

        if(doesOrderExits){
            OrderO orderO = orderRepo.findOrderOByCustomerAndOrdered(customer, false).get();
            if (doesProductExits){

                if(productVariationRepo.findById(orderProductVariation).isPresent()){
                    Product product = productRepo.findById(orderProductRequest.getProductId()).get();
                    ProductVariation productVariation = productVariationRepo.findById(orderProductVariation).get();
                    List<OrderProduct> orderProductList = orderProductRepo.findOrderProductsByProductAndProductVariation(product, productVariation);

                    // sort order products by appendices if they have them or update existing order product
                    if(orderProductList.size() == 0){

                        System.out.println("Order product does not exists");
                        OrderProduct orderProduct = new OrderProduct();

                        String dateTime = Long.toString(currentTimeMillis());
                        String orderProductUUIDString = dateTime + product.getNameOfProduct();
                        String orderProductUUID = UUID.nameUUIDFromBytes(orderProductUUIDString.getBytes()).toString();

                        orderProduct.setUuid(orderProductUUID);
                        orderProduct.setProduct(product);
                        orderProduct.setOrdered(false);
                        orderProduct.setOrderO(orderO);
                        orderProduct.setTimeCreated(now());
                        orderProduct.setTimeUpdated(now());
                        orderProduct.setQuantity(orderProductRequest.getQuantity());
                        orderO.getProductList().add(orderProduct);
                        orderProduct.updatePrice();
//
//                        if(product.getVariation() != null){
//                            System.out.println(product.getVariation());
//                            if(product.getVariation().getProductVariationList().size() > 0){
//                                addProductVariations(product, orderProduct, orderProductVariation);
//                            }
//                        }

                        orderProductRepo.save(orderProduct);
                        orderRepo.save(orderO);

                        return orderProduct;

                    } else if (orderProductList.size() == 1) {
                        System.out.println("Order product exits");

                        OrderProduct orderProduct = orderProductList.get(0);
                        orderProduct.setTimeUpdated(now());
                        orderProduct.setQuantity(orderProductRequest.getQuantity());
                        orderProduct.updatePrice();

                        orderProductRepo.save(orderProduct);

                        return orderProduct;
                    } else {
                        throw new Exception("There are same multiple products");
                    }
                } else {
                    throw new Exception("Product variation does not exists");
                }
            } else {
                throw new Exception("Product dont exist");
            }
        } else {
            if (doesProductExits){
                // get order and add order product to exiting order
                // create an order and then add products

                OrderO orderO = new OrderO();
                String id = orderO.getId().toString();
                String dateTime = Long.toString(currentTimeMillis());
                String totalUUID = id + dateTime;

                String uuid = UUID.nameUUIDFromBytes(totalUUID.getBytes()).toString();

                orderO.setCustomer(userProfileService.returnCustomer(userRepo.findByEmail(principal.getName()).orElseThrow()));
//                orderO.setUserProfile(userRepo.findByUsername(principal.getName()));
                orderO.setDelivered(false);
                orderO.setOrdered(false);
                orderO.setPickedUp(false);
                orderO.setPrepared(false);
                orderO.setRefundRequested(false);
                orderO.setRefundGranted(false);
                orderO.setStartTime(now());
                orderO.setUuid(uuid);

                Product product = productRepo.findById(orderProductRequest.getProductId()).get();
                OrderProduct orderProduct = new OrderProduct();

                String orderProductUUIDString = dateTime + product.getNameOfProduct();
                String orderProductUUID = UUID.nameUUIDFromBytes(orderProductUUIDString.getBytes()).toString();

                orderProduct.setUuid(orderProductUUID);
                orderProduct.setProduct(product);
                orderProduct.setOrdered(false);
                orderProduct.setOrderO(orderO);
                orderProduct.setTimeCreated(now());
                orderProduct.setTimeUpdated(now());
                orderProduct.setQuantity(orderProductRequest.getQuantity());
                orderO.getProductList().add(orderProduct);
                orderProduct.updatePrice();

//                if(product.getVariation() != null){
//                    System.out.println(product.getVariation());
//                    if(product.getVariation().getProductVariationList().size() > 0){
//                        addProductVariations(product, orderProduct, orderProductVariation);
//                    }
//                }

                orderProductRepo.save(orderProduct);
                orderRepo.save(orderO);

                return orderProduct;
            } else {
                throw new Exception("Product dont exist");
            }
        }
    }

    @Override
    public OrderProduct get(Long id) {
        return null;
    }

    @Override
    public OrderProduct update(Long id, OrderProductUpdateRequest orderProductUpdateRequest, Principal principal) throws Exception {
        OrderProduct orderProduct = orderProductRepo.findById(id)
                .orElseThrow(() -> new Exception("Order product not found"));

        Long orderProductId = orderProductUpdateRequest.getOrderProductId();
        int quantity = orderProductUpdateRequest.getQuantity();

        return null;
    }

    @Override
    public String addToOrder(Long id, Principal principal) throws Exception {
        OrderProduct orderProduct = orderProductRepo.findById(id)
                .orElseThrow(() -> new Exception("Order product not found"));

        OrderO order = orderProduct.getOrderO();
        User user = userRepo.findByEmail(principal.getName())
                .orElseThrow(() -> new Exception("User not found"));
        if (order.getCustomer().getUser() == user) {
            orderProduct.setInOrder(true);
            orderProduct.setTimeUpdated(now());
            orderProductRepo.save(orderProduct);
            return "OK";
        } else {
            return "This product is not in your order";
        }
    }


    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public String deleteOrderProduct(Long id, Principal principal) throws Exception {
        if (orderProductRepo.findById(id).isEmpty()){
            return "OK";
        }

        OrderProduct orderProduct = orderProductRepo.findById(id).get();

        User user = userRepo.findByEmail(principal.getName())
                .orElseThrow(() -> new Exception("User not found"));
        if (orderProduct.getOrderO().getCustomer().getUser() == user){
            orderProductRepo.delete(orderProduct);
            return "Product deleted";
        } else {
            throw new Exception("Error while deleting product");
        }
    }

    @Override
    public void addAppendicesToOrderProduct(Product product, Map<Long, List<Long>> data, OrderProduct orderProduct) throws Exception{
        for ( Map.Entry<Long, List<Long>> entry : data.entrySet()) {
            Long appendicesCatId = entry.getKey();
            List appendicesId = entry.getValue();
            if(product.getAppendicesCategoryList() != null) {
                if(appendicesCategoryRepo.findById(appendicesCatId).isPresent()){
                    AppendicesCategory appendicesCategory = appendicesCategoryRepo.findById(appendicesCatId).get();
                    List<Appendices> appendicesList = appendicesCategory.getAppendicesList();

                    if(appendicesId.size() > appendicesCategory.getNumberOfAllowed()){
                        throw new Exception("More than allowed");
                    }

                    if(appendicesList != null){
                        AppendicesCategoryOrderProduct appendicesCategoryOrderProduct = new AppendicesCategoryOrderProduct();
//                        List<Appendices> appendicesList1 = appendicesCategoryOrderProduct.getAppendicesList();
//                        for(Appendices app : appendicesList){
//                            if(appendicesId.contains(app.getId())){
//                                appendicesList1.add(app);
//                            } else {
//                                throw new Exception("This appendix is not in this category");
//                            }
//                        }
                        orderProduct.getAppendicesCategoryList().add(appendicesCategoryOrderProduct);
                    } else {
                        throw new Exception("Appendices list doesn't exist");
                    }
            }
            } else {
                throw new Exception("Appendices category doesnt exist");
            }
        }


    }

//    @Override
//    public void addProductVariations(Product product, OrderProduct orderProduct, Long orderProductVariation) throws Exception{
//            if(product.getVariation().getProductVariationList().size() > 0){
//                if(productVariationRepo.findById(orderProductVariation).isPresent()){
//                    ProductVariation productVariation = productVariationRepo.findById(orderProductVariation).get();
//                    if(product == productVariation.getVariation().getProduct()){
//                        orderProduct.setProductVariation(productVariation);
//                    } else {
//                        throw new Exception("Product doesn't have specified variation");
//                    }
//                } else {
//                    throw new Exception("This variation doesn't exist");
//                }
//            } else {
//                throw new Exception("Doesn't have product variations");
//            }
//    }
}



// add appendices individually

// get category
// get items in category
// check if items belong to category
// add item to order product
// return price of order product

//                        AppendicesCategory appendicesCategory = appendicesCategoryRepo.findById(mapOfAppendices.keySet()[0]);
//
//                        if(appendicesCategoryRepo.findById(mapOfAppendices.get(0)).isPresent()){
//                            AppendicesCategory appendicesCategory = appendicesCategoryRepo.findById(orderProductVariation).get();
//                            if(product == productVariation.getVariation().getProduct()){
//                                orderProduct.setProductVariation(productVariation);
//                            } else {
//                                throw new Exception("Product doesn't have specified variation");
//                            }
//                        } else {
//                            throw new Exception("This variation doesn't exist");
//                        }