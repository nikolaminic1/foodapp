package com.example.foodapp.business.service.admin_service.implementation;

import com.example.foodapp.auth.repo.UserRepository;
import com.example.foodapp.auth.service.admin.service.BusinessOwnerAdminService;
import com.example.foodapp.business.enumeration.BusinessRequestStatus;
import com.example.foodapp.business.model.Requests.BusinessRequest;
import com.example.foodapp.business.model.Requests.BusinessRequestReview;
import com.example.foodapp.business.repo.BusinessRepo;
import com.example.foodapp.business.repo.BusinessRequestRepo;
import com.example.foodapp.business.service.admin_service.AdminBusinessRequestService;
import com.example.foodapp.business.service.admin_service.AdminBusinessService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

import static com.example.foodapp.api_resources.SendEmail.sendEmailGrantBusinessRequest;

@RequiredArgsConstructor
@Service
@Transactional
@Log4j2
public class AdminBusinessRequestServiceImplementation implements AdminBusinessRequestService {
    private final UserRepository userRepo;
    private final BusinessRepo businessRepo;
    private final BusinessRequestRepo businessRequestRepo;
    private final AdminBusinessService adminBusinessService;
    private final BusinessOwnerAdminService businessOwnerAdminService;

    @Override
    public BusinessRequest create(BusinessRequest business) {
        return null;
    }

    @Override
    public Collection<BusinessRequest> list(int page, int limit) {
        return businessRequestRepo.findAll(PageRequest.of(page, limit)).toList();
    }

    @Override
    public BusinessRequest get(Long id) {
        return null;
    }

    @Override
    public BusinessRequest update(Long id, BusinessRequestReview businessRequestReview) {
        Optional<BusinessRequest> businessRequestOptional = businessRequestRepo.findById(id);

        if (businessRequestOptional.isPresent()){
            BusinessRequest businessRequest = businessRequestOptional.get();
            businessRequest.setIsGranted(businessRequestReview.getStatus());

            boolean doesBusinessExits = businessOwnerAdminService.doesBusinessOwnerExists(
                    businessRequest.getUser());

            if(doesBusinessExits){
                System.out.println(doesBusinessExits);
                return null;
            }

            if(businessRequestReview.getStatus().equals(BusinessRequestStatus.GRANTED)){
                businessRequest.setGrantInfo(businessRequestReview.getGrantInfo());
                if (businessRequest.getUser() != null){
                    log.info("admin business request granted");
                    adminBusinessService.create(businessRequest.getUser());
                    sendEmailGrantBusinessRequest("email", "granted");
                }
            } else {
                log.warn("admin business request denied");
                businessRequest.setDeniedInfo(businessRequestReview.getDeniedInfo());
                sendEmailGrantBusinessRequest("email", "denied");
            }

            businessRequest.setReviewed(true);
            businessRequestRepo.save(businessRequest);

            return businessRequest;
        }

        return null;
    }

    @Override
    public BusinessRequest grantRequest(Long id, BusinessRequest businessRequest) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
