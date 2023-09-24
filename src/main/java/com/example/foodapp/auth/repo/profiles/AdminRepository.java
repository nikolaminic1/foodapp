package com.example.foodapp.auth.repo.profiles;

import com.example.foodapp.auth.user.UserProfile;
import com.example.foodapp.auth.user.UserProfiles.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByUsername(String username);
    Admin findByUserProfile(UserProfile userProfile);
}
