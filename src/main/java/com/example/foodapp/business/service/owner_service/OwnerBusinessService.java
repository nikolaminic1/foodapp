package com.example.foodapp.business.service.owner_service;


import com.example.foodapp.business.model.Business;
import com.example.foodapp.business.model.Requests.BusinessUpdateRequest;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

public interface OwnerBusinessService {
    Business get(Principal principal) throws Exception;
    Business update(BusinessUpdateRequest businessUpdateRequest, Principal principal) throws Exception;
    String uploadBackgroundImage(MultipartFile file, Principal principal) throws Exception;
    String uploadLogoImage(MultipartFile file, Principal principal) throws Exception;
    void deleteBackgroundImage(Principal principal) throws Exception;
    void deleteLogoImage(Principal principal) throws Exception;
    Boolean delete(Long id);
}
