package com.example.foodapp.business.repo;

import com.example.foodapp.auth.user.User;
import com.example.foodapp.business.model.Requests.BusinessRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusinessRequestRepo extends JpaRepository<BusinessRequest, Long> {
    Optional<BusinessRequest> findById(Long id);
    void deleteBusinessRequestById(Long id);
    Boolean existsBusinessRequestByBusinessRegistrationNumber(String id);
    BusinessRequest findBusinessRequestByUser(User user);
    Boolean existsBusinessRequestByUser(User user);
}
