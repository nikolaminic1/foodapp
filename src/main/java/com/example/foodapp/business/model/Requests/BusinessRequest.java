package com.example.foodapp.business.model.Requests;

import com.example.foodapp.auth.user.User;
import com.example.foodapp.business.enumeration.BusinessRequestStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String contactEmail;
    private String contactPhone;
    private String request;
    private BusinessRequestStatus isGranted;
    private boolean isReviewed;
    private String grantInfo;
    private String deniedInfo;
    private String businessRegistrationNumber;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    @JsonIgnore
//    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

}
