package com.example.foodapp.auth.repo;

import com.example.foodapp.auth.user.Addresses.Address;
import com.example.foodapp.auth.user.Addresses.BillingAddress;
import com.example.foodapp.auth.user.Addresses.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepo extends JpaRepository<Address, Long> {
//    Optional<Address> findAddressByShippingAddresses(ShippingAddress shippingAddress);
//    Optional<Address> findAddressByBillingAddresses(BillingAddress billingAddress);
}
