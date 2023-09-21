package com.example.foodapp.auth.user.UserProfiles;

import com.example.foodapp.auth.user.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity(name = "profile_type")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class _Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Role role;

    @Column(name = "profile_t_id")
    private Long profileId;
}
