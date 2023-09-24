package com.example.foodapp.auth.user.UserProfiles;


import com.example.foodapp.auth.user.ERole;
import com.example.foodapp.auth.user.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Log4j2
public class Customer {
    private ERole EROLE = ERole.CUSTOMER;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;

    @CreationTimestamp
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    private LocalDateTime dateUpdated;

    @OneToOne
    private UserProfile userProfile;

//    @OneToMany
//    private List<OrderO> orderList;
}
