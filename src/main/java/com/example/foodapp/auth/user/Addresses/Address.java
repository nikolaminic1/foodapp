package com.example.foodapp.auth.user.Addresses;


import com.example.foodapp.auth.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.lang.reflect.Array;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(exclude = {"user",})
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "billing_addresses",
            joinColumns = @JoinColumn(name = "billing_addresses"),
            inverseJoinColumns = @JoinColumn(name = "user"))
    private Set<BillingAddress> billingAddresses = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "shipping_addresses",
            joinColumns = @JoinColumn(name = "shipping_addresses"),
            inverseJoinColumns = @JoinColumn(name = "user"))
    private Set<ShippingAddress> shippingAddresses = new HashSet<>();

//    @OneToOne(mappedBy = "address")
//    @JsonBackReference
//    private User user;

    public Set<BillingAddress> getBillingAddresses() {
        return billingAddresses;
    }

    public void setBillingAddresses(Set<BillingAddress> billingAddresses) {
        this.billingAddresses = billingAddresses;
    }

    public Set<ShippingAddress> getShippingAddresses() {
        return shippingAddresses;
    }

    public void setShippingAddresses(Set<ShippingAddress> shippingAddresses) {
        this.shippingAddresses = shippingAddresses;
    }

//    public Integer getUser() {
//        return this.user.getId();
//    }
//
//    public void setUser(User user) {
//        this.user = user;
////        user.setAddress(this);
//    }

    public HashMap getAddresses () {
        HashMap add = new HashMap();
        add.put("id", this.getId());
        add.put("billingAddresses", Collections.singleton(this.getBillingAddresses()));
        add.put("shippingAddresses", Collections.singleton(this.getShippingAddresses()));
        return add;
    }
}
