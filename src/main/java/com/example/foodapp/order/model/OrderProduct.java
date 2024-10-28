package com.example.foodapp.order.model;


import com.example.foodapp.order.serializer.OrderProduct_ProductSerializer;
import com.example.foodapp.product.model.Appendices;
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
import java.util.List;

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
    private boolean isOrder;

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

//    public void addAppendicesCategories(List<Map<Long, List<Long>>> appCat){
//        for (Map<Long, List<Long>> appSingle:  appCat) {
//            this.appendicesCategoryList.add(appSingle);
//        }
//    }

    public double getPrice() {
        double totalPrice = 0;
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

        if(this.getAppendicesCategoryList() != null && this.getAppendicesCategoryList().size() > 0){
            for(AppendicesCategoryOrderProduct appendicesCategoryOrderProduct: this.getAppendicesCategoryList()){
//                List<Appendices> appendicesList = appendicesCategoryOrderProduct.getAppendicesList();
//                if(appendicesList != null && appendicesList.size() > 0){
//                    for(Appendices appendices : appendicesList){
//                        if(appendices.getDoesAffectPrice()){
//                            totalPrice += appendices.getPrice();
//                        }
//                    }
//                }
            }
        }
        this.price = totalPrice;
        return totalPrice;
    }

    public void updatePrice(){
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
}
