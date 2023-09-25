package com.example.foodapp.auth.service;

import com.example.foodapp.auth.dto.*;
import com.example.foodapp.auth.repo.UserRepository;
import com.example.foodapp.auth.user.ERole;
import com.example.foodapp.auth.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String register(RegisterRequest request) throws Exception {

        String email = request.getEmail();
        boolean doesUserExists = userRepository.findByEmail(email).isPresent();

        if (email == null
                || request.getName() == null) {
            throw new Exception("Data should be provided.");
        }

        if (doesUserExists) {
            throw new Exception("User with provided email already exists.");
        }

        var user = User.builder()
                .firstname(request.getName())
//                .lastname(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .ERole(ERole.USER)
                .build();

        user.setEnabled(true);
        user.setAccountNonLocked(true);
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        System.out.println(user.isEnabled());
        userRepository.save(user);
        return "OK";

    }

    public String activate(ConfirmationRequestResponse request) {
        return "OK";
    }

    public User getMyProfile(Principal principal) throws Exception{
        boolean doesUserExists = userRepository.findByEmail(principal.getName()).isPresent();
        if (!doesUserExists) {
            throw new Exception("User does not exists");
        }
        return userRepository.findByEmail(principal.getName()).get();
    }

    public ConfirmationRequestResponse deleteMyProfile(Principal principal, LoginRequest request) throws Exception{
        throw new Exception("Error");
    }

    public String deleteProfileConfirmation (ConfirmationRequestResponse request) throws Exception {
        throw new Exception("Error");
    }

    public String resetPassword(ResetPasswordRequest request) {
        return "Password reset";
    }

    public String resetPasswordConfirm(ResetPasswordConfirmRequest request) {
        return "Password reset confirm";
    }
}
