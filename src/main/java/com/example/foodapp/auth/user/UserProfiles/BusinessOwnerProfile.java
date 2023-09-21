package com.example.foodapp.auth.user.UserProfiles;


import com.example.foodapp.auth.user.BusinessOwner;
import com.example.foodapp.auth.user.Role;
import com.example.foodapp.auth.user.UserProfile;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "business_owner_profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessOwnerProfile {
    private Role ROLE = Role.BUSINESS;

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
