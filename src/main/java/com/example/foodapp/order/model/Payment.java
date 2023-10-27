package com.example.foodapp.order.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.MERGE, mappedBy = "payment")
    @JsonBackReference
    private OrderO orderO;

    private double amount;
    private String stripeChargeId;
    private String stripeCustomerId;
    private Boolean isCharged;

    @CreationTimestamp
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    private LocalDateTime dateUpdated;

    private LocalDateTime datePaid;
    private LocalDateTime dateAccounted;

    public void setOrderO(OrderO orderO){
        this.orderO = orderO;
        orderO.setPayment(this);
    }
}
