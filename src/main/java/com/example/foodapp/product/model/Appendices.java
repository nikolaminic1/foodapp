package com.example.foodapp.product.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"appendicesCategory"})
@EqualsAndHashCode(exclude = {"appendicesCategory"})
public class Appendices {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nameOfAppendices;
    private Boolean doesAffectPrice;
    private double price;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinTable(name = "appendicesListTable")
    private AppendicesCategory appendicesCategory;

    @OneToOne(cascade = CascadeType.ALL)
    private Image image;
}
