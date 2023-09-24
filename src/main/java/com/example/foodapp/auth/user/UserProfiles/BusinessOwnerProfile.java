package com.example.foodapp.auth.user.UserProfiles;


import com.example.foodapp.auth.user.BusinessOwner;
import com.example.foodapp.auth.user.ERole;
import com.example.foodapp.auth.user.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "business_owner_profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessOwnerProfile {
    private ERole EROLE = ERole.BUSINESS;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String businessOwnerRow;
    private String taxCode;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;

    @OneToOne
    private UserProfile userProfile;

//    @OneToOne
//    private Business business;

    @ManyToOne
    private BusinessOwner businessOwner;

}
