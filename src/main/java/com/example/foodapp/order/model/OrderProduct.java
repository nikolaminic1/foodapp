package com.example.foodapp.order.model;


import com.example.foodapp.order.serializer.OrderProduct_ProductSerializer;
import com.example.foodapp.product.model.SideDish;
import com.example.foodapp.product.model.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"sideDishes"})
@EqualsAndHashCode(exclude = {"sideDishes"})
@Log4j2
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String uuid;
    private boolean ordered;
    private boolean inOrder;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonSerialize(using = OrderProduct_ProductSerializer.class)
    private Product product;

//    @ManyToOne(cascade = CascadeType.MERGE)
//    private ProductVariation productVariation;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "orderProducts")
    @JsonManagedReference
    private List<SideDish> sideDishes;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonBackReference
    private OrderO orderO;

    @CreationTimestamp
    private LocalDateTime timeCreated;

    @UpdateTimestamp
    private LocalDateTime timeUpdated;

    private LocalDateTime timeOrdered;
    private LocalDateTime timePrepared;

    private int quantity;
    private double price;

    public void setOrderO(OrderO orderO) {
        this.orderO = orderO;
        if (orderO.getProductList() != null){
            orderO.getProductList().add(this);
        }
    }

    public double getItemPrice() {
        double total = 0.0;
        total += this.getProduct().getPriceOfProduct();
        if (this.getSideDishes() != null) {
            for(SideDish sideDish: this.getSideDishes()) {
                if (sideDish.getDoesAffectPrice()) {
                    total += sideDish.getPrice();
                }
            }
        }
        return total;
    }

    public double getTotalPrice() {
        return this.quantity * this.getItemPrice();
    }

    public Map<String, Object> getOrderProductDetail() {
        Map<String, Object> product = new HashMap<>();
        product.put("id", this.getId());
        product.put("ordered", this.isOrdered());
        product.put("inOrder", this.isInOrder());
        product.put("product", this.getProduct());
        product.put("sideDishes", this.getSideDishes());
        product.put("timeCreated", this.getTimeCreated());
        product.put("timeUpdated", this.getTimeUpdated());
        product.put("timeOrdered", this.getTimeOrdered());
        product.put("quantity", this.getQuantity());
        product.put("price", this.getItemPrice());
        product.put("totalPrice", this.getTotalPrice());
        return product;
    }

    public void updatePrice(){
        this.price = getItemPrice();
    }
        

//        if(appendicesCategoryList != null){
//            log.warn("product var price 6");
//            for (SideDishCategoryOrderProduct appendicesCategory : appendicesCategoryList) {
//                log.warn("product var price 7");
//                double appendicesCategoryPrice = 0;
////                for (SideDish appendices : appendicesCategory.getSideDishList()) {
////                    appendicesCategoryPrice =+ appendices.getPrice();
////                    System.out.println(appendices.getPrice());
////                    log.warn("product var price 8");
////                }
//                log.warn("product var price 9");
//                price =+ appendicesCategoryPrice;
//            }
//        }
    

    public List<Object> getCustomerSideDishCategories() {
        List<Object> list = new ArrayList<>();

        return list;
    }
}

//        if(this.productVariation != null){
//            if(this.productVariation.getDoesAffectPrice()){
//                if(this.productVariation.getIsOnDiscount() != null){
//                    if(this.productVariation.getIsOnDiscount()){
//                        totalPrice = this.productVariation.getPriceOfVariationDiscount();
//                    }
//                } else {
//                    totalPrice = this.productVariation.getPriceOfVariation();
//                }
//            }
//        } else {
//            if(this.product.getIsOnDiscount()){
//                totalPrice = this.product.getDiscountPrice();
//            } else {
//                totalPrice = this.product.getPriceOfProduct();
//            }
//        }

//        System.out.println(productVariation.getPriceOfVariation());
//        if(productVariation != null){
//            log.warn("product var price 1");
//            if(productVariation.getDoesAffectPrice()){
//                log.warn("product var price 2");
//                price = productVariation.getTotalPrice();
//                log.warn("product var price 3");
//            }
//        } else {
//            log.warn("product var price 4");
//            price = product.getPriceOfProduct();
//            log.warn("product var price 5");
//        }


//
//        if (categories!= null){
//                for (SideDishCategory category : categories){
//                Map<String, Object> categoryDetail = new HashMap<>();
//        categoryDetail.put("id", category.getId());
//        categoryDetail.put("nameOfCategory", category.getNameOfCategory());
//        categoryDetail.put("isRequired", category.getIsRequired());
//        categoryDetail.put("numberOfAllowed", category.getNumberOfAllowed());
//
//        List<Object> objectsList = new ArrayList<>();
//
//        for (SideDish sideDish : category.getSideDishList()) {
//        Map<String, Object> newSD = new HashMap<>();
//        newSD.put("id", sideDish.getId());
//        newSD.put("nameOfSideDish", sideDish.getNameOfSideDish());
//        newSD.put("doesAffectPrice", sideDish.getDoesAffectPrice());
//        newSD.put("price", sideDish.getPrice());
//
//        if (categoryOrderProducts != null) {
//        log.error('1');
//        for (SideDishCategoryOrderProduct categoryOrderProduct : categoryOrderProducts){
//        if (categoryOrderProduct.getSideDishCategory() == category) {
//        if (categoryOrderProduct.getSideDishList().contains(sideDish)) {
//        newSD.put("inOrder", true);
//        } else {
//        newSD.put("inOrder", false);
//        }
//        break;
//        } else {
//        newSD.put("inOrder", false);
//        }
//        if (categoryDetail.get("isMax") == null) {
//        if (category.getNumberOfAllowed() < categoryOrderProduct.getSideDishList().size()){
//        categoryDetail.put("isMax", false);
//        } else {
//        categoryDetail.put("isMax", true);
//        }
//        } else {
//        categoryDetail.put("isMax", true);
//        }
//
//        }
//        } else {
//        newSD.put("inOrder", false);
//        categoryDetail.put("isMax", false);
//        }
//
//        objectsList.add(newSD);
//        }
//
//
//        categoryDetail.put("sideDishes", objectsList);
//        list.add(categoryDetail);
//        }
//        }

//
//    List<SideDishCategory> categoryList = this.product.getSideDishCategoryList();
//    List<SideDishCategoryOrderProduct> categoryOrderProducts = this.getSideDishCategoryList();
//    List<Boolean> booleans = new ArrayList<>();
//
//        if (categoryList == null || categoryList.size() == 0) {
//                return true;
//                }
//
//                for (SideDishCategory categoryOrderProduct : categoryList){
//                if (categoryOrderProduct.getIsRequired() && categoryOrderProducts == null) {
//                return false;
//                }
//                for (SideDishCategoryOrderProduct cat : categoryOrderProducts) {
//                if (cat.getSideDishCategory() == categoryOrderProduct) {
//                if (categoryOrderProduct.getIsRequired() && cat.getSideDishList().size() > 0){
//                booleans.add(true);
//                } else if (categoryOrderProduct.getIsRequired() && cat.getSideDishList().size() == 0) {
//                booleans.add(false);
//                } else {
//                booleans.add(true);
//                }
//                }
//                }
//
//                }
//
//                for (Boolean value : booleans) {
//                if (!value) {
//                return false;
//                }
//                }
