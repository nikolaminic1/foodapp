package com.example.foodapp.auth.repo;

import com.example.foodapp.auth.dto.PasswordResetToken;
import com.example.foodapp.auth.dto.UserDeleteRequest;
import com.example.foodapp.auth.dto.UserDeleteToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DeleteTokenRepo extends JpaRepository<UserDeleteToken, UUID> {
    List<UserDeleteToken> findUserDeleteTokensByEmail(String email);
    Optional<UserDeleteToken> findUserDeleteTokenByUid(UUID uuid);
}
