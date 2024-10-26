package com.example.foodapp.auth.user.UserProfiles;

import com.example.foodapp.auth.user.ERole;
import com.example.foodapp.auth.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "admins")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin{
    private ERole EROLE = ERole.ADMIN;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String adminRow;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
}
