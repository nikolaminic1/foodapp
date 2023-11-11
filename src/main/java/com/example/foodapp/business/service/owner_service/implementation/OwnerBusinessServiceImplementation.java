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
import com.example.foodapp.business.service.owner_service.OwnerBusinessService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.security.Principal;
import java.util.List;

import static com.example.foodapp.api_resources.ImageFileSaveService.saveFile;
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

    private void setTimeOpened(Business business, TimeOpenedWeekRequest request) {
        TimeOpenedWeek openedWeek = business.getTimeOpened();
       openedWeek.getTimeOpenedDayMonday().setTimeOpen(request.getMonday().getTimeOpen());
       openedWeek.getTimeOpenedDayMonday().setTimeClose(request.getMonday().getTimeClose());
       openedWeek.getTimeOpenedDayMonday().setIsNonStop(request.getMonday().getIsNonStop());

       openedWeek.getTimeOpenedDayTuesday().setTimeOpen(request.getTuesday().getTimeOpen());
       openedWeek.getTimeOpenedDayTuesday().setTimeClose(request.getTuesday().getTimeClose());
       openedWeek.getTimeOpenedDayTuesday().setIsNonStop(request.getTuesday().getIsNonStop());

       openedWeek.getTimeOpenedDayWednesday().setTimeOpen(request.getWednesday().getTimeOpen());
       openedWeek.getTimeOpenedDayWednesday().setTimeClose(request.getWednesday().getTimeClose());
       openedWeek.getTimeOpenedDayWednesday().setIsNonStop(request.getWednesday().getIsNonStop());

       openedWeek.getTimeOpenedDayThursday().setTimeOpen(request.getThursday().getTimeOpen());
       openedWeek.getTimeOpenedDayThursday().setTimeClose(request.getThursday().getTimeClose());
       openedWeek.getTimeOpenedDayThursday().setIsNonStop(request.getThursday().getIsNonStop());

       openedWeek.getTimeOpenedDayFriday().setTimeOpen(request.getFriday().getTimeOpen());
       openedWeek.getTimeOpenedDayFriday().setTimeClose(request.getFriday().getTimeClose());
       openedWeek.getTimeOpenedDayFriday().setIsNonStop(request.getFriday().getIsNonStop());

       openedWeek.getTimeOpenedDaySaturday().setTimeOpen(request.getSaturday().getTimeOpen());
       openedWeek.getTimeOpenedDaySaturday().setTimeClose(request.getSaturday().getTimeClose());
       openedWeek.getTimeOpenedDaySaturday().setIsNonStop(request.getSaturday().getIsNonStop());

       openedWeek.getTimeOpenedDaySunday().setTimeOpen(request.getSunday().getTimeOpen());
       openedWeek.getTimeOpenedDaySunday().setTimeClose(request.getSunday().getTimeClose());
       openedWeek.getTimeOpenedDaySunday().setIsNonStop(request.getSunday().getIsNonStop());
    }
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
    public Business get(Principal principal) throws Exception {
        String email = principal.getName();
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new Exception("Not found"));
        return businessRepo.findBusinessByBusinessOwner_User(user)
                .orElseThrow(() -> new Exception("Business not found"));
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
        business.setPriceOfOrderForFreeDelivery(request.getPriceOfOrderForFreeDelivery());
    //        setTimeOpened(business, request.getTimeOpened());
    //        setBusinessTags(business, request.getTags());
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
