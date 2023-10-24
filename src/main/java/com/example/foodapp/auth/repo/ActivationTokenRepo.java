package com.example.foodapp.auth.repo;

import com.example.foodapp.auth.dto.ActivationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ActivationTokenRepo extends JpaRepository<ActivationToken, UUID> {
    Optional<ActivationToken> findActivationTokenById(UUID id);
    Optional<ActivationToken> findActivationTokenByUid(UUID id);
}
