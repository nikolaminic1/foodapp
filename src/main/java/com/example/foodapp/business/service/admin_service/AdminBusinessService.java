package com.example.foodapp.business.service.admin_service;


import com.example.foodapp.api_resources.ImageType;
import com.example.foodapp.auth.user.User;
import com.example.foodapp.business.model.Business;
import com.example.foodapp.business.model.Requests.BusinessUpdateRequest;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.Collection;

public interface AdminBusinessService {
    Business createOrUpdate(BusinessUpdateRequest request, Principal principal) throws Exception;
    String list(int page, int limit, Principal principal) throws Exception;
    String get(Long id, Principal principal) throws Exception;
    Boolean delete(Long id, Principal principal) throws Exception;

    Boolean addImage(MultipartFile file, ImageType imageType, Long businessId, Principal principal) throws Exception;
}
