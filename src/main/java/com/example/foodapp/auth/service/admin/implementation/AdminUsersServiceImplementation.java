package com.example.foodapp.auth.service.admin.implementation;

import com.example.foodapp._api.PageableRequest;
import com.example.foodapp._api.PaginatedResponse;
import com.example.foodapp._api.PaginatedResponseSerializer;
import com.example.foodapp.auth.repo.UserRepository;
import com.example.foodapp.auth.serializers.AdminUsersSerializer;
import com.example.foodapp.auth.service.admin.service.AdminUsersService;
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

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AdminUsersServiceImplementation implements AdminUsersService {
    private final UserRepository userRepository;

    @Override
    public String list(PageableRequest request, Principal principal) throws Exception {
        Pageable pageable = PageRequest.of(0, 3);
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
    public void deleteUser(Long id, Principal principal) throws Exception {
        userRepository.deleteUserById(id);
    }

    @Override
    public String get(Long id, Principal principal) throws Exception {
        User user = userRepository.findUserById(id)
                .orElseThrow(() -> new Exception("User with provided id does not exist"));
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(User.class, new AdminUsersSerializer.DetailSerializer());
        mapper.registerModule(module);
        return mapper.writeValueAsString(user);
    }
}
