package com.example.foodapp.business.service.owner_service.implementation;

import com.example.foodapp.auth.repo.BusinessOwnerRepo;
import com.example.foodapp.auth.repo.UserRepository;
import com.example.foodapp.auth.user.User;
import com.example.foodapp.auth.user.UserProfiles.BusinessOwner;
import com.example.foodapp.business.model.Business;
import com.example.foodapp.business.model.BusinessTag;
import com.example.foodapp.business.model.Requests.BusinessUpdateRequest;
import com.example.foodapp.business.model.Requests.TimeOpenedWeekRequest;
import com.example.foodapp.business.model.TimeOpenedWeek;
import com.example.foodapp.business.repo.BusinessRepo;
import com.example.foodapp.business.repo.BusinessTagRepo;
import com.example.foodapp.business.serializers.owner.OwnerRestaurantSerializer;
import com.example.foodapp.business.service.owner_service.OwnerBusinessService;
import com.example.foodapp.product.serializers.restaurant.RestaurantProductCategorySerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.security.Principal;
import java.util.List;

import static com.example.foodapp.api_resources.ImageFileSaveService.saveFile;
import static com.example.foodapp.business.model.Requests.BusinessUpdateRequest.parseDate;
import static com.example.foodapp.business.model.Requests.BusinessUpdateRequest.setTimeOpened;
import static java.lang.Boolean.TRUE;


@RequiredArgsConstructor
@Service
@Transactional
@Log4j2
public class OwnerBusinessServiceImplementation implements OwnerBusinessService {
    private final BusinessRepo businessRepo;
    private final UserRepository userRepo;
    private final BusinessOwnerRepo businessOwnerRepo;
    private final BusinessTagRepo businessTagRepo;

    private void setBusinessTags(Business business, List<Long> request) throws Exception {
        for (Long id: request) {
            BusinessTag tag = businessTagRepo
                    .findBusinessTagById(id)
                    .orElseThrow(() -> new Exception("Tag is not found"));
            if (!business.getTags().contains(tag)){
                business.getTags().add(tag);
            }
        }
    }

    @Override
    public String get(Principal principal) throws Exception {
        String email = principal.getName();
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new Exception("Not found"));

        Business business = businessRepo.findBusinessByBusinessOwner_User(user)
                .orElseThrow(() -> new Exception("Business not found"));
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Business.class, new OwnerRestaurantSerializer.Detail());
        mapper.registerModule(module);
        return mapper.writeValueAsString(business);
    }

    @Override
    public Business update(BusinessUpdateRequest request, Principal principal) throws Exception {
        User user = userRepo
                .findByEmail(principal.getName())
                .orElseThrow(() -> new Exception("User not found"));
        Business business = businessRepo
                .findBusinessByBusinessOwner_User(user)
                .orElseThrow(() -> new Exception("Business not found"));
        business.setName(request.getName());
        business.setDescription(request.getDescription());
        business.setPriceOfDelivery(request.getPriceOfDelivery());
        setTimeOpened(business, request);
//            setBusinessTags(business, request.getTags());
        businessRepo.save(business);
        return business;
    }

    @Override
    public String uploadBackgroundImage(MultipartFile file, Principal principal) throws Exception {
        User user = userRepo
                .findByEmail(principal.getName())
                .orElseThrow(() -> new Exception("User not found"));
        Business business = businessRepo
                .findBusinessByBusinessOwner_User(user)
                .orElseThrow(() -> new Exception("Business not found"));
        String path = saveFile(business.getName(), file);
        business.setBackgroundImage(path);
        System.out.println(path);
        businessRepo.save(business);
        return "OK";
    }

    @Override
    public String uploadLogoImage(MultipartFile file, Principal principal) throws Exception {
        User user = userRepo
                .findByEmail(principal.getName())
                .orElseThrow(() -> new Exception("User not found"));
        Business business = businessRepo
                .findBusinessByBusinessOwner_User(user)
                .orElseThrow(() -> new Exception("Business not found"));
        String path = saveFile(business.getName(), file);
        System.out.println(path);
        business.setLogoImage(path);
        businessRepo.save(business);
        return "OK";
    }

    @Override
    public void deleteBackgroundImage(Principal principal) throws Exception {
        User user = userRepo
                .findByEmail(principal.getName())
                .orElseThrow(() -> new Exception("User not found"));
        Business business = businessRepo
                .findBusinessByBusinessOwner_User(user)
                .orElseThrow(() -> new Exception("Business not found"));
        business.setBackgroundImage(null);
        businessRepo.save(business);
    }

    @Override
    public void deleteLogoImage(Principal principal) throws Exception {
        User user = userRepo
                .findByEmail(principal.getName())
                .orElseThrow(() -> new Exception("User not found"));
        Business business = businessRepo
                .findBusinessByBusinessOwner_User(user)
                .orElseThrow(() -> new Exception("Business not found"));
        business.setLogoImage(null);
        businessRepo.save(business);
    }

    @Override
    public Boolean delete(Long id) {
        businessRepo.deleteById(id);
        return TRUE;
    }


}
