package com.example.foodapp.business.service.admin_service.implementation;

import com.example.foodapp.api_resources.ImageType;
import com.example.foodapp.auth.repo.BusinessOwnerRepo;
import com.example.foodapp.auth.repo.UserRepository;
import com.example.foodapp.auth.serializers.UserCustomerSerializer;
import com.example.foodapp.auth.user.*;
import com.example.foodapp.business.model.Business;
import com.example.foodapp.business.model.Requests.BusinessUpdateRequest;
import com.example.foodapp.business.repo.BusinessRepo;
import com.example.foodapp.business.serializers.BusinessListSerializer;
import com.example.foodapp.business.service.admin_service.AdminBusinessService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static com.example.foodapp.api_resources.ImageFileSaveService.saveFile;
import static java.lang.Boolean.TRUE;
import static org.springframework.data.domain.PageRequest.of;

@RequiredArgsConstructor
@Service
@Transactional
@Log4j2
public class AdminBusinessServiceImplementation implements AdminBusinessService {
    private final BusinessRepo businessRepo;
    private final UserRepository userRepo;
    private final BusinessOwnerRepo businessOwnerRepo;

    public Business create(User user, Principal principal) {
        if(user != null){
            Business business = new Business();

            // it saves multiple times
            // TODO add roles for business users
//            Set<Role> roles = new HashSet<>();
//            Optional<Role> userRole = roleRepository.findByName(ERole.USER);
//            Optional<Role> userBusinessRole = roleRepository.findByName(ERole.BUSINESS);
//            userRole.ifPresent(roles::add);
//            userBusinessRole.ifPresent(roles::add);

//            UserProfile userProfile = user;

//            BusinessOwner businessOwner = new BusinessOwner();
//            businessOwner.setBusiness(business);
//            businessOwner.setUserProfile(userProfile);
//            businessOwner.setIsActive(false);
//            userProfile.setBusinessOwnerBoolean(true);
//            userProfile.setBusinessOwner(businessOwner);
//            userProfile.setStaff(true);
//            userProfileRepo.save(userProfile);
//            user.setRoles(roles);
            userRepo.save(user);
            businessRepo.save(business);
            return null;
        }
        return null;
    }

    @Override
    public String list(int page, int limit, Principal principal) throws Exception {
        List<Business> businessList = businessRepo.findAll(of(page, limit)).toList();
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(new BusinessListSerializer.Serializer());
        mapper.registerModule(module);
        return mapper.writeValueAsString(businessList);
    }

    @Override
    public String get(Long id, Principal principal) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Business.class, new BusinessListSerializer.DetailSerializer());
        mapper.registerModule(module);
        return mapper.writeValueAsString(businessRepo.findBusinessById(id).orElseThrow(() -> new Exception("Not found")));
    }

    @Override
    public Boolean delete(Long id, Principal principal) throws Exception {
        businessRepo.deleteById(id);
        return TRUE;
    }

    @Override
    public String createOrUpdate(Long id, BusinessUpdateRequest request, Principal principal) throws Exception {
        System.out.println(id);
        System.out.println(request);

        Business business;
        if (id==null) {
            business = new Business();
        } else {
            business = businessRepo.findBusinessById(id)
                    .orElseThrow(() -> new Exception("The business with provided ID does not exist."));
        }

        business.setName(request.getName());
        business.setDescription(request.getDescription());
        business.setPriceOfDelivery(request.getPriceOfDelivery());
        businessRepo.save(business);
        return "Successfully updated.";
    }

    @Override
    public Boolean addImage(MultipartFile file, ImageType imageType, Long businessId, Principal principal) throws Exception {
        Business business = businessRepo.findBusinessById(businessId)
                .orElseThrow(() -> new Exception("The business with provided ID does not exist."));

        String fileString;

        if (file != null) {
            fileString = saveFile(file.getName(), file);
            if (fileString.equals("File is not saved")){
                throw new Exception("File is not saved.");
            }
        } else {
            throw new Exception("File is not sent.");
        }

        if (Objects.equals(imageType.toString(), ImageType.BUSINESS_LOGO_IMAGE.toString())) {
            business.setLogoImage(fileString);
            return true;
        }

        if (Objects.equals(imageType.toString(), ImageType.BUSINESS_BACKGROUND_IMAGE.toString())) {
            business.setBackgroundImage(fileString);
            return true;
        }

        return false;
    }
}
