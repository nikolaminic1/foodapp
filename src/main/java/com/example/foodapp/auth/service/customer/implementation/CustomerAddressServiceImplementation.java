package com.example.foodapp.auth.service.customer.implementation;

import com.example.foodapp.auth.dto.addresses.AddressRequest;
import com.example.foodapp.auth.repo.*;
import com.example.foodapp.auth.service.customer.service.CustomerAddressService;
import com.example.foodapp.auth.user.Addresses.AddressModel;
import com.example.foodapp.auth.user.Addresses.AddressType;
import com.example.foodapp.auth.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CustomerAddressServiceImplementation implements CustomerAddressService {
    private final UserRepository userRepo;
    private final AddressModelRepo addressRepo;
    private final BillingAddressRepo billingAddressRepo;
    private final ShippingAddressRepo shippingAddressRepo;

    @Override
    public AddressModel createOrUpdate(AddressRequest request, Principal principal) throws Exception {
        User user = userRepo.findByEmail(principal.getName())
                .orElseThrow(() -> new Exception("Invalid user"));
        AddressModel address;
        System.out.println(request.getId());
        if (request.getId() == null) {
            address = new AddressModel();
        } else {
            address = addressRepo.findById(request.getId())
                    .orElseThrow(() -> new Exception("Invalid address ID"));
            if (address.getUser() != user) {
                throw new Exception("Invalid user or address ID");
            }
        }

        address.setBuildingNumber(request.getBuildingNumber());
        address.setCityNumber(request.getCityNumber());
        address.setFlatNumber(request.getFlatNumber());
        address.setZipCode(request.getZipCode());
        address.setStreetName(request.getStreetName());
        address.setAddressType(request.getAddressType());
        address.setUser(user);

        if (request.isDefault()) {
            List<AddressModel> addressList = addressRepo.getAddressModelsByUser(user);
            List<AddressModel> addressByType = addressList.stream().filter((add) -> {
                        if (request.getAddressType() != null){
                            return add.getAddressType().equals(request.getAddressType());
                        }
                            return false;
                    }
            ).toList();

            addressByType.forEach((add) -> {
                add.setDefault(false);
            });
            address.setDefault(true);
            addressRepo.saveAll(addressByType);
        }

        addressRepo.save(address);
        return address;
    }

    @Override
    public AddressModel get(Long id, Principal principal) throws Exception {
        User user = userRepo.findByEmail(principal.getName())
                .orElseThrow(() -> new Exception("Invalid user"));
        AddressModel address = addressRepo.findById(id)
                .orElseThrow(() -> new Exception("Not found"));
        if (address.getUser() == user){
            return address;
        } else {
            throw new Exception("This address does not belong to you");
        }
    }

    @Override
    public void delete(Long id, Principal principal) throws Exception {
        User user = userRepo.findByEmail(principal.getName())
                .orElseThrow(() -> new Exception("Invalid user"));
        AddressModel address = addressRepo.findById(id)
                .orElseThrow(() -> new Exception("Address does not exists"));
        if (address.getUser() == user){
            user.getAddresses().remove(address);
            address.setUser(null);
            addressRepo.delete(address);
        } else {
            throw new Exception("Address with provided ID does not belong to you");
        }
    }

}
