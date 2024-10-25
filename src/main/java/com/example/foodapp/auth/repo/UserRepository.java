package com.example.foodapp.auth.repo;

import com.example.foodapp.auth.user.Addresses.Address;
import com.example.foodapp.auth.user.User;
import com.example.foodapp.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Page<User> findAll(Pageable pageable);
    Optional<User> findByEmail(String email);
    Optional<User> findUserById(Long id);
    Boolean existsByEmail(String email);
    Boolean deleteUserById(Long id);
    void deleteByEmail(String username);
//    List<Address> findByNameContaining(String username);

}
