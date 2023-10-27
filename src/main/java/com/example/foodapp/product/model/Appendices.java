package com.example.foodapp.product.model;

import com.example.foodapp.business.serializers.View;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
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
    @JsonView(View.Public.class)
    private Long id;
    @JsonView(View.Public.class)
    private String nameOfAppendices;
    @JsonView(View.Public.class)
    private Boolean doesAffectPrice;
    @JsonView(View.Public.class)
    private double price;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinTable(name = "appendicesListTable")
    @JsonView(View.Public.class)
    private AppendicesCategory appendicesCategory;

    @OneToOne(cascade = CascadeType.MERGE)
    @JsonView(View.Public.class)
    private Image image;
}
