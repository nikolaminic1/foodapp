package com.example.foodapp.order.model;

import com.example.foodapp.auth.user.Addresses.BillingAddress;
import com.example.foodapp.auth.user.Addresses.ShippingAddress;
import com.example.foodapp.auth.user.UserProfiles.Customer;
import com.example.foodapp.business.model.Business;
import com.example.foodapp.order.enumeration.DeliveryType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonSerialize(using = OrderO_OrderSerializer.class)
public class OrderO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String uuid;

//    @ManyToOne(cascade = CascadeType.MERGE)
//    @JsonBackReference
//    private UserProfile userProfile;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonBackReference
    private Customer customer;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "orderO", fetch = FetchType.EAGER)
    @JsonManagedReference
    @JsonIgnore
    private List<OrderProduct> productList;

    @CreationTimestamp
    private LocalDateTime startTime;

    @UpdateTimestamp
    private LocalDateTime timeUpdated;

    private LocalDateTime orderedTime;
    private LocalDateTime timePrepared;
    private LocalDateTime timeShipped;
    private LocalDateTime timeDelivered;

    private Boolean ordered;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonManagedReference
    private ShippingAddress shippingAddress;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonManagedReference
    private BillingAddress billingAddress;

    private DeliveryType deliveryType;

    @OneToOne(cascade = CascadeType.MERGE)
    @JsonManagedReference
    private Payment payment;

    @OneToOne(cascade = CascadeType.MERGE)
    @JsonManagedReference
    private Coupon coupon;

    private Boolean prepared;
    private Boolean pickedUp;
    private Boolean delivered;
    private Boolean refundRequested;
    private Boolean refundGranted;
    private double deliveryPrice;
    private double price;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Business business;

    public void updatePrice() {
        this.setPrice(this.getPrice());
    }

    public void setPrice(double amount){
        this.price = amount;
    }

    public List<OrderProduct> getProducts() {
        List<OrderProduct> products = new ArrayList<>();
        if (this.getProductList() == null) {
            return products;
        }
        for(OrderProduct orderProduct : this.getProductList()){
            if (orderProduct.isInOrder()){
                products.add(orderProduct);
            }
        }
        return products;
    }


    // todo : need to check is delivery free based on business
    public double getTotalPrice(){
        double totalOrderPrice = 0;
        if (this.getProductList() == null) {
            return totalOrderPrice;
        }
        for(OrderProduct orderProduct : this.getProductList()){
            if (orderProduct.isInOrder()){
                totalOrderPrice = totalOrderPrice + orderProduct.getTotalPrice();
            }

        }
        totalOrderPrice += this.deliveryPrice;
        return totalOrderPrice;
    }


}
