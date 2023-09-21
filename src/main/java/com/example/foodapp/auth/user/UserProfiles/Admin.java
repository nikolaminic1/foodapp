package com.example.foodapp.auth.user.UserProfiles;

import com.example.foodapp.auth.user.Role;
import com.example.foodapp.auth.user.UserProfile;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "admins")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin{
    private Role ROLE = Role.ADMIN;

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String adminRow;

//    @OneToOne
//    private UserProfile userProfile;
}
