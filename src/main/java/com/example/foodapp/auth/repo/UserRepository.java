package com.example.foodapp.auth.repo;

import com.example.foodapp.auth.user.Addresses.Address;
import com.example.foodapp.auth.user.User;
import com.example.foodapp.product.model.Product;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findAll(Pageable pageable);
    Optional<User> findByEmail(String email);
    Optional<User> findUserById(Integer id);
    Boolean existsByEmail(String email);
    @Transactional
    @Modifying
    @Query("""
            UPDATE Token token
            set token.user=null
            where token.user.id=:id
            """)
    void removeUserAssociation(@Param("id") Integer id);

    void deleteUserById(@NotNull Integer id);
    void deleteByEmail(String username);
//    List<Address> findByNameContaining(String username);

}
