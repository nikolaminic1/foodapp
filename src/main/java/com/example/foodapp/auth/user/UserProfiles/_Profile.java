package com.example.foodapp.auth.user.UserProfiles;

import com.example.foodapp.auth.user.ERole;
import lombok.*;

import jakarta.persistence.*;

@Data
@Builder
@Entity(name = "_profile")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profiles")
public class _Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private ERole ERole;

    @Column(name = "profile_t_id")
    private Long profileId;
}
