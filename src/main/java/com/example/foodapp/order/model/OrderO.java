package com.example.foodapp.order.model;

import com.example.foodapp.auth.user.Addresses.BillingAddress;
import com.example.foodapp.auth.user.Addresses.ShippingAddress;
import com.example.foodapp.auth.user.UserProfiles.Customer;
import com.example.foodapp.order.enumeration.DeliveryType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JsonBackReference
//    private UserProfile userProfile;


    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderO", fetch = FetchType.EAGER)
    @JsonManagedReference
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private ShippingAddress shippingAddress;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private BillingAddress billingAddress;

    private DeliveryType deliveryType;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private Payment payment;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private Coupon coupon;

    private Boolean prepared;
    private Boolean pickedUp;
    private Boolean delivered;
    private Boolean refundRequested;
    private Boolean refundGranted;
    private Boolean isDeliveryFree;
    private double deliveryPrice;
    private double price;

    public void setPrice(double amount){
        this.price = amount;
    }

    public double getPrice(){
        double totalOrderPrice = 0;
        for(OrderProduct orderProduct : this.getProductList()){
            totalOrderPrice = totalOrderPrice + orderProduct.getPrice();
        }

        if(this.isDeliveryFree != null){
            if(!this.isDeliveryFree){
                totalOrderPrice += this.deliveryPrice;
            }
        }

        return totalOrderPrice;
    }
}
