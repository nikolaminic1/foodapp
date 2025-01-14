package com.example.foodapp.auth.user.Addresses;

import com.example.foodapp.auth.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "user")
@ToString(exclude = "user")
public class AddressModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String streetName;
    private int buildingNumber;
    private int flatNumber;
    private int zipCode;
    private String cityNumber;
    private AddressType addressType;
    private boolean isDefault;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonBackReference
    @JsonIgnore
//    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

}
