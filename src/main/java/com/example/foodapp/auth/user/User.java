package com.example.foodapp.auth.user;

import com.example.foodapp.auth.user.UserProfiles._Profile;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "authorities",
//            joinColumns = @JoinColumn(name = "username"),
//            inverseJoinColumns = @JoinColumn(name = "authority"))
//    private Set<Role> roles;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_table_id")
    private _Profile profile;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return ERole.getAuthorities();
//        return List.of(new SimpleGrantedAuthority((ERole.name())));
    }

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
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
