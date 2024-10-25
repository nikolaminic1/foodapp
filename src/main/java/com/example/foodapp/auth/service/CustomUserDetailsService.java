package com.example.foodapp.auth.service;

import com.example.foodapp.auth.repo.UserRepository;
import com.example.foodapp.auth.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//@Service
//@RequiredArgsConstructor
public class CustomUserDetailsService
//        implements UserDetailsService
{

////    @Autowired
//    private final UserRepository userRepository;
//
//    @Transactional  // Ensures session is active
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        System.out.println("-------------");
//        User user = userRepository.findByEmail(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
//
//        // Initialize roles to avoid lazy loading issues
//        System.out.println(user.getRole());  // This forces Hibernate to load the roles within the session
//
//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(),
//                user.getPassword(),
//                user.getAuthorities()  // Use authorities mapped from roles if needed
//        );
//    }
}
