package com.example.foodapp.auth.user;

import com.example.foodapp.auth.dto.Gender;
import com.example.foodapp.auth.user.Addresses.AddressModel;
import com.example.foodapp.auth.user.Addresses.AddressType;
import com.example.foodapp.auth.user.UserProfiles._Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;

    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;
    private String phone;
    private Gender gender;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "authorities",
//            joinColumns = @JoinColumn(name = "username"),
//            inverseJoinColumns = @JoinColumn(name = "authority"))
//    private Set<Role> roles;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = {CascadeType.DETACH})
    @JsonManagedReference
    @JsonIgnore
    private List<AddressModel> addresses;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_table_id")
    private _Profile profile;

//    public HashMap<String, List<AddressModel>> getAddresses() {
//        List<AddressModel> billingAddresses = this.addresses.stream().filter((add) ->
//           add.getAddressType().equals(AddressType.BILLING)
//        ).collect(Collectors.toList());
//
//        List<AddressModel> shippingAddresses = this.addresses.stream().filter((add) ->
//                add.getAddressType().equals(AddressType.SHIPPING)
//        ).collect(Collectors.toList());
//
//        HashMap<String, List<AddressModel>> add = new HashMap<>();
//        add.put("billing_address", billingAddresses);
//        add.put("shipping_address", shippingAddresses);
//        return add;
//    }

//    public HashMap<String, Object> getAddresses() {
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("lsdbn", "laksdnf");
//        return map;
//    }

    public void setProfileObject(_Profile profile){
        this.profile = profile;
    }

//    public void setUser(User user){
//        this.user = user;
//        user.setUserProfile(this);
//    }

//    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
//    @JoinColumn
//            (name = "user_profile_id", referencedColumnName = "id")
//    @JsonManagedReference
//    private UserProfile userProfile;

    @Enumerated(EnumType.STRING)
    private ERole ERole;

    public String getRole(){
        return this.ERole.toString();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return ERole.getAuthorities();
//        return List.of(new SimpleGrantedAuthority((ERole.name())));
    }

//    public HashMap<String, Set<Object>> getAddresses() {
//        if (this.address != null) {
//            return this.address.getAddresses();
//        }
//        return null;
//    }


    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

    public Map<String, Object> getBasicUserDetail() {
        Map<String, Object> detail = new HashMap<>();
        detail.put("id", this.id);
        detail.put("firstname", this.firstname);
        detail.put("lastname", this.lastname);
        detail.put("email", this.email);
        detail.put("isEnabled", this.isEnabled);
        detail.put("phone", this.phone);
        detail.put("gender", this.gender);
        return detail;
    }

}
