package com.example.foodapp.auth.repo.profiles;

import com.example.foodapp.auth.user.UserProfiles._Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface _ProfileRepository extends JpaRepository<_Profile, Long> {
    Optional<_Profile> findById(Long id);
}
