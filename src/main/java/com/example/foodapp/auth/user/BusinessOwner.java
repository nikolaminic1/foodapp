package com.example.foodapp.auth.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "business_owner")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"userProfile",
//        "business"
})
@EqualsAndHashCode(exclude = {"userProfile",
//        "business"
})
public class BusinessOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean isActive;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_profile_id", referencedColumnName = "id")
    @JsonBackReference
    private UserProfile userProfile;

//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JsonBackReference
//    private Business business;

//    public Business getBusiness() {
//        return business;
//    }
//
//    public void setBusiness(Business business) {
//        this.business = business;
//    }
}
