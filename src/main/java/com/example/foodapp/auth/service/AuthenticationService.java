package com.example.foodapp.auth.service;

import com.example.foodapp.auth.config.JwtService;
import com.example.foodapp.auth.dto.*;
import com.example.foodapp.auth.repo.TokenRepository;
import com.example.foodapp.auth.repo.UserRepository;
//import com.example.foodapp.auth.repo.profiles._ProfileRepo;
import com.example.foodapp.auth.repo.profiles._ProfileRepository;
import com.example.foodapp.auth.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;

    private final _ProfileRepository profileRepo;

    public AuthenticationResponse login(LoginRequest request) throws Exception {

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new Exception("User with provided email does not exist"));

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        return AuthenticationResponse.builder()
                .access(jwtService.generateToken(user))
                .refresh(jwtService.generateRefreshToken(user))
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty()) {
            return;
        }

        validUserTokens.forEach(token -> {
            token.setRevoked(true);
            token.setExpired(true);
        });

        tokenRepository.saveAll(validUserTokens);
    }

    public AuthenticationResponse refreshToken (
            String token
    ) throws Exception {
        final String refreshToken;
        final String accessToken;
        final String userEmail;

        if (token == null || !token.startsWith("Bearer ")) {
//            return "Refresh token should be provided.";
            throw new Exception("Error");
        }

        refreshToken = token.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);

        if (userEmail != null) {
            var user = userRepository.findByEmail(userEmail)
                    .orElseThrow();

            if (jwtService.isTokenValid(refreshToken, user)) {
                revokeAllUserTokens(user);
                accessToken = jwtService.generateToken(user);
                String newRefreshToken = jwtService.generateRefreshToken(user);
                saveUserToken(user, accessToken);
                saveUserToken(user, newRefreshToken);
                return AuthenticationResponse.builder()
                        .access(accessToken)
                        .refresh(newRefreshToken)
                        .build();
            }
        } else {
            throw new Exception("Error");
        }
        throw new Exception("Error");
    }

    public String verify(String token) throws Exception{
        if (token == null || !token.startsWith("Bearer ")) {
            throw new Exception("Error");
        }
        final String tokenToVerify = token.substring(7);
        final String userEmail = jwtService.extractUsername(tokenToVerify);

        if (userEmail == null) {
            throw new Exception("Token is not valid");
        }

        var user = userRepository.findByEmail(userEmail)
                .orElseThrow();

        if (!jwtService.isTokenValid(tokenToVerify, user)) {
            throw new Exception("Token is not valid");
        }

        return "OK";
    }
}
