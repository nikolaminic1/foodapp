package com.example.foodapp.order.model;


import com.example.foodapp.order.serializer.OrderProduct_ProductSerializer;
import com.example.foodapp.product.model.Appendices;
import com.example.foodapp.product.model.AppendicesCategory;
import com.example.foodapp.product.model.Product;
import com.example.foodapp.product.model.ProductVariation;
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
@ToString(exclude = {"appendicesCategoryList"})
@EqualsAndHashCode(exclude = {"appendicesCategoryList"})
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
    private List<AppendicesCategoryOrderProduct> appendicesCategoryList;

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

    public boolean isAddToCartAllowed() {
        List<AppendicesCategory> categoryList = this.product.getAppendicesCategoryList();
        List<AppendicesCategoryOrderProduct> categoryOrderProducts = this.getAppendicesCategoryList();
        List<Boolean> booleans = new ArrayList<>();

        if (categoryList == null || categoryList.size() == 0) {
            return true;
        }

        for (AppendicesCategory categoryOrderProduct : categoryList){
            if (categoryOrderProduct.getIsRequired() && categoryOrderProducts == null) {
                return false;
            }
            for (AppendicesCategoryOrderProduct cat : categoryOrderProducts) {
                if (cat.getAppendicesCategory() == categoryOrderProduct) {
                    if (categoryOrderProduct.getIsRequired() && cat.getAppendicesList().size() > 0){
                        booleans.add(true);
                    } else if (categoryOrderProduct.getIsRequired() && cat.getAppendicesList().size() == 0) {
                        booleans.add(false);
                    } else {
                        booleans.add(true);
                    }
                }
            }

        }

        for (Boolean value : booleans) {
            if (!value) {
                return false;
            }
        }

        return true;
    }

    public double getItemPrice() {
        double total = 0.0;
        total += this.getProduct().getPriceOfProduct();
        if (this.getAppendicesCategoryList() != null
            && this.getAppendicesCategoryList().size() > 0) {
            for(AppendicesCategoryOrderProduct category:
                    this.getAppendicesCategoryList()) {
                total += category.getCategoryTotal();
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
        product.put("appendicesCategoryList", this.getAppendicesCategoryList());
        product.put("timeCreated", this.getTimeCreated());
        product.put("timeUpdated", this.getTimeUpdated());
        product.put("timeOrdered", this.getTimeOrdered());
        product.put("quantity", this.getQuantity());
        product.put("price", this.getItemPrice());
        product.put("totalPrice", this.getTotalPrice());
        return product;
    }

    public void updatePrice(){
        if(appendicesCategoryList != null){
            log.warn("product var price 6");
            for (AppendicesCategoryOrderProduct appendicesCategory : appendicesCategoryList) {
                log.warn("product var price 7");
                double appendicesCategoryPrice = 0;
//                for (Appendices appendices : appendicesCategory.getAppendicesList()) {
//                    appendicesCategoryPrice =+ appendices.getPrice();
//                    System.out.println(appendices.getPrice());
//                    log.warn("product var price 8");
//                }
                log.warn("product var price 9");
                price =+ appendicesCategoryPrice;
            }
        }
    }

    public List<Object> getCustomerSideDishCategories() {
        List<Object> list = new ArrayList<>();
        List<AppendicesCategory> categories = this.product.getAppendicesCategoryList();
        List<AppendicesCategoryOrderProduct> categoryOrderProducts = this.getAppendicesCategoryList();

        if (categories!= null){
            for (AppendicesCategory category : categories){
                Map<String, Object> categoryDetail = new HashMap<>();
                categoryDetail.put("id", category.getId());
                categoryDetail.put("nameOfCategory", category.getNameOfCategory());
                categoryDetail.put("isRequired", category.getIsRequired());
                categoryDetail.put("numberOfAllowed", category.getNumberOfAllowed());

                List<Object> objectsList = new ArrayList<>();

                for (Appendices appendices : category.getAppendicesList()) {
                    Map<String, Object> newSD = new HashMap<>();
                    newSD.put("id", appendices.getId());
                    newSD.put("nameOfSideDish", appendices.getNameOfAppendices());
                    newSD.put("doesAffectPrice", appendices.getDoesAffectPrice());
                    newSD.put("price", appendices.getPrice());

                    if (categoryOrderProducts != null) {
                        log.error('1');
                        for (AppendicesCategoryOrderProduct categoryOrderProduct : categoryOrderProducts){
                            if (categoryOrderProduct.getAppendicesCategory() == category) {
                                if (categoryOrderProduct.getAppendicesList().contains(appendices)) {
                                    newSD.put("inOrder", true);
                                } else {
                                    newSD.put("inOrder", false);
                                }
                                break;
                            } else {
                                newSD.put("inOrder", false);
                            }
                            if (categoryDetail.get("isMax") == null) {
                                if (category.getNumberOfAllowed() < categoryOrderProduct.getAppendicesList().size()){
                                    categoryDetail.put("isMax", false);
                                } else {
                                    categoryDetail.put("isMax", true);
                                }
                            } else {
                                categoryDetail.put("isMax", true);
                            }

                        }
                    } else {
                        newSD.put("inOrder", false);
                        categoryDetail.put("isMax", false);
                    }

                    objectsList.add(newSD);
                }


                categoryDetail.put("sideDishes", objectsList);
                list.add(categoryDetail);
            }
        }
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