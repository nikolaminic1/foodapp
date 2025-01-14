package com.example.foodapp.auth.user.UserProfiles;

import com.example.foodapp.auth.user.User;
import com.example.foodapp.business.model.Business;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "business_owner")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"user",
//        "business"
})
@EqualsAndHashCode(exclude = {"user",
//        "business"
})
public class BusinessOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Boolean isActive;
    private String taxCode;

    @CreationTimestamp
    private LocalDateTime dateCreated;
    @UpdateTimestamp
    private LocalDateTime dateUpdated;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinColumn(name = "user_profile_id", referencedColumnName = "id")
    @JsonBackReference
//    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JsonBackReference
    private Business business;

//    public Business getBusiness() {
//        return business;
//    }
//
//    public void setBusiness(Business business) {
//        this.business = business;
//    }
}
