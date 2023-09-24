package com.example.foodapp.auth.repo.profiles;

import com.example.foodapp.auth.user.User;
import com.example.foodapp.auth.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepo extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findById(Long id);
    boolean existsById(Long id);
    void deleteUserProfileById(Long id);
    UserProfile findByUser(User user);
//    void deleteUserProfileByUser(User user);
//    void deleteAllByUser(User user);
}
