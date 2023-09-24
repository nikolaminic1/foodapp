package com.example.foodapp.auth.user;

//import com.example.foodapp.auth.user.Addresses.Address;
//import com.example.foodapp.auth.user.UserProfiles._Profile;
import com.example.foodapp.auth.user.Addresses.Address;
import com.example.foodapp.auth.user.UserProfiles._Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import jakarta.persistence.*;

@Entity
@Table(name = "user_profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//@ToString(exclude = {"businessOwner"})
//@EqualsAndHashCode(exclude = {"businessOwner"})
@Log4j2
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    private String surname;
    private String gender;
    private String phoneNumber;
    private String stripeId;
    private String email;
    private boolean oneClickPurchasing;
    private boolean isStaff;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_addresses",
            joinColumns = @JoinColumn(name = "address"),
            inverseJoinColumns = @JoinColumn(name = "user"))

    private Address addresses;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonIgnore
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private User user;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_table_id")
    private _Profile profile;

//    public void setProfileObject(_Profile profile){
//        this.profile = profile;
//    }

    public void setUser(User user){
        this.user = user;
//        user.setUserProfile(this);
    }

}
