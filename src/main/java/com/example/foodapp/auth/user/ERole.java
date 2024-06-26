package com.example.foodapp.auth.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.foodapp.auth.user.Permission.*;

@RequiredArgsConstructor
public enum ERole {
//    USER(Collections.emptySet()),
    CUSTOMER(Set.of(
        CUSTOMER_READ,
        CUSTOMER_UPDATE,
        CUSTOMER_DELETE,
        CUSTOMER_CREATE
)),
    ADMIN(
            Set.of(
                    CUSTOMER_READ,
                    CUSTOMER_UPDATE,
                    CUSTOMER_DELETE,
                    CUSTOMER_CREATE,
                    ADMIN_READ,
                    ADMIN_UPDATE,
                    ADMIN_DELETE,
                    ADMIN_CREATE,
                    BUSINESS_READ,
                    BUSINESS_UPDATE,
                    BUSINESS_DELETE,
                    BUSINESS_CREATE
            )
    ),
    BUSINESS(
            Set.of(
                    BUSINESS_READ,
                    BUSINESS_UPDATE,
                    BUSINESS_DELETE,
                    BUSINESS_CREATE
            )
    );

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
