package com.example.foodapp.auth.repo;

import com.example.foodapp.auth.dto.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PasswordTokenRepo extends JpaRepository<PasswordResetToken, UUID> {
    List<PasswordResetToken> findPasswordResetTokensByEmail(String email);
    Optional<PasswordResetToken> findPasswordResetTokenByUid(UUID uuid);
}
