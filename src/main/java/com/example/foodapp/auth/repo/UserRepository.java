package com.example.foodapp.auth.repo;

import com.example.foodapp.auth.user.Addresses.Address;
import com.example.foodapp.auth.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
    void deleteByEmail(String username);
//    List<Address> findByNameContaining(String username);

}
