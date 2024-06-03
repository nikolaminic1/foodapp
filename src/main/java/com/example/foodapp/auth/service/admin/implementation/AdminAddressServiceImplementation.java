package com.example.foodapp.auth.service.admin.implementation;

import com.example.foodapp.auth.dto.addresses.AddressRequest;
import com.example.foodapp.auth.repo.AddressModelRepo;
import com.example.foodapp.auth.service.admin.service.AdminAddressService;
import com.example.foodapp.auth.user.Addresses.AddressModel;
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
public class AdminAddressServiceImplementation implements AdminAddressService {
    private final AddressModelRepo addressRepo;

    @Override
    public AddressModel update(AddressRequest request, Principal principal) throws Exception {
        AddressModel address = addressRepo.findById(request.getId())
                .orElseThrow(() -> new Exception("Invalid address ID"));

        address.setBuildingNumber(request.getBuildingNumber());
        address.setCityNumber(request.getCityNumber());
        address.setFlatNumber(request.getFlatNumber());
        address.setZipCode(request.getZipCode());
        address.setAddressType(request.getAddressType());
        address.setStreetName(request.getStreetName());

        if (request.isDefault()) {
            List<AddressModel> addressList = addressRepo.getAddressModelsByUser(address.getUser());
            List<AddressModel> addressByType = addressList.stream().filter((add) ->
                    add.getAddressType().equals(request.getAddressType())
            ).toList();

            addressByType.forEach((add) -> {
                add.setDefault(false);
            });
            addressRepo.saveAll(addressByType);
        }
        address.setDefault(true);
        addressRepo.save(address);
        return address;
    }

    @Override
    public void delete(Long id, Principal principal) throws Exception {
        addressRepo.deleteById(id);
    }

    @Override
    public AddressModel get(Long id, Principal principal) throws Exception {
        return addressRepo.findById(id)
                .orElseThrow(() -> new Exception("Invalid ID"));
    }

    @Override
    public List<AddressModel> getAll(Principal principal) throws Exception {
        return addressRepo.findAll();
    }
}
