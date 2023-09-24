package com.example.foodapp.auth.repo;

import com.example.foodapp.auth.user.ERole;
import com.example.foodapp.auth.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Long> {
    //    Role findByAuthority(String authority);
    // list of roles error
    Optional<Role> findByName(ERole name);
}
