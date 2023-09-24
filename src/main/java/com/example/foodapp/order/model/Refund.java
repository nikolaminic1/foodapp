package com.example.foodapp.order.model;


import com.example.foodapp.auth.user.UserProfiles.Customer;
import com.example.foodapp.order.enumeration.RefundStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Refund {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private OrderO orderO;

    @ManyToOne
    private Customer customer;

    private String reason;
    private RefundStatus refundStatus;
    private String email;
    private LocalDateTime dateSent;
    private LocalDateTime dateReviewed;
    private LocalDateTime dateResolved;
}
