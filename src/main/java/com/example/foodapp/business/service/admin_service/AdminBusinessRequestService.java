package com.example.foodapp.business.service.admin_service;


import com.example.foodapp.business.model.Requests.BusinessRequest;
import com.example.foodapp.business.model.Requests.BusinessRequestReview;

import java.util.Collection;

public interface AdminBusinessRequestService {
    BusinessRequest create(BusinessRequest businessRequest);
    Collection<BusinessRequest> list(int page, int limit);
    BusinessRequest get(Long id);
    BusinessRequest update(Long id, BusinessRequestReview businessRequestReview);
    BusinessRequest grantRequest(Long id, BusinessRequest businessRequest);
    Boolean delete(Long id);
}
