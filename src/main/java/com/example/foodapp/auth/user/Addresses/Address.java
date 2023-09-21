package com.example.foodapp.auth.user.Addresses;


import com.example.foodapp.auth.user.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "billing_addresses",
            joinColumns = @JoinColumn(name = "billing_addresses"),
            inverseJoinColumns = @JoinColumn(name = "user"))
    private Set<Address> billingAddresses = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "shipping_addresses",
            joinColumns = @JoinColumn(name = "shipping_addresses"),
            inverseJoinColumns = @JoinColumn(name = "user"))
    private Set<Address> shippingAddresses = new HashSet<>();

    @OneToOne
    private UserProfile userProfile;

    public Set<Address> getBillingAddresses() {
        return billingAddresses;
    }

    public void setBillingAddresses(Set<Address> billingAddresses) {
        this.billingAddresses = billingAddresses;
    }

    public Set<Address> getShippingAddresses() {
        return shippingAddresses;
    }

    public void setShippingAddresses(Set<Address> shippingAddresses) {
        this.shippingAddresses = shippingAddresses;
    }
}
