package com.example.foodapp.auth.service.admin.implementation;

import com.example.foodapp._api.PageableRequest;
import com.example.foodapp._api.PaginatedResponse;
import com.example.foodapp._api.PaginatedResponseSerializer;
import com.example.foodapp.auth.dto.Token;
import com.example.foodapp.auth.dto.UserUpdatedRequest;
import com.example.foodapp.auth.repo.TokenRepository;
import com.example.foodapp.auth.repo.UserRepository;
import com.example.foodapp.auth.serializers.AdminUsersSerializer;
import com.example.foodapp.auth.service.admin.service.AdminUsersService;
import com.example.foodapp.auth.user.ERole;
import com.example.foodapp.auth.user.User;
import com.example.foodapp.product.model.Product;
import com.example.foodapp.product.serializers.restaurant.RestaurantProductSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

import static com.example.foodapp._api.NumericCheck.convertToInt;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AdminUsersServiceImplementation implements AdminUsersService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    @Override
    public String list(PageableRequest request, Principal principal) throws Exception {
        Pageable pageable = PageRequest.of(
                request.getPage(),
                request.getLimit()
        );
        var users = userRepository.findAll(pageable).toList();
        System.out.println(users);
//        PaginatedResponse<User> data = new PaginatedResponse<>(users);
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        mapper.registerModule(new JavaTimeModule());
//        module.addSerializer(PaginatedResponse.class, new PaginatedResponseSerializer());
        module.addSerializer(new AdminUsersSerializer.ListSerializer());
        mapper.registerModule(module);
        return mapper.writeValueAsString(users);
    }

    @Override
    public void updateUser(Integer id, UserUpdatedRequest request, Principal principal) throws Exception {
        // todo write logic to update user
        try {
            User user = userRepository.findUserById(id)
                    .orElseThrow(() -> new Exception("User not found."));
            user.setFirstname(request.getFirstname());
            user.setLastname(request.getLastname());
            user.setGender(request.getGender());
            user.setPhone(request.getPhone());

            if (Objects.equals(request.getRole(), "CUSTOMER")) {
                user.setERole(ERole.CUSTOMER);
            } else if (Objects.equals(request.getRole(), "ADMIN")) {
                user.setERole(ERole.ADMIN);
            } else if (Objects.equals(request.getRole(), "BUSINESS")) {
                user.setERole(ERole.BUSINESS);
            }
            userRepository.save(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteUser(Integer id, Principal principal) throws Exception {
//        User user = userRepository.findByEmail(principal.getName())
//                .orElseThrow(() -> new Exception("User does not exist"));
//        List<Token> tokens = tokenRepository.findAllTokensByUser(user);
//        System.out.println(tokens);
//        for (Token token: tokens) {
//            token.setUser(null);
//        }
//        tokenRepository.saveAll(tokens);
        userRepository.removeUserAssociation(id);
        userRepository.deleteUserById(Objects.requireNonNull(convertToInt(id)));
    }

    @Override
    public String get(Integer id, Principal principal) throws Exception {
        User user = userRepository.findUserById((id))
                .orElseThrow(() -> new Exception("User with provided id does not exist"));
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(User.class, new AdminUsersSerializer.DetailSerializer());
        mapper.registerModule(module);
        return mapper.writeValueAsString(user);
    }
}
