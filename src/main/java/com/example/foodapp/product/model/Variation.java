package com.example.foodapp.product.model;


import com.fasterxml.jackson.annotation.*;
import lombok.*;

import jakarta.persistence.*;

import java.util.List;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"product"})
@EqualsAndHashCode(exclude = {"product"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Variation {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String name;
    private Boolean isRequired;

    @OneToOne(cascade = CascadeType.ALL)
//    @JsonSerialize(using = Variation_ProductSerializer.class)
    @JsonBackReference
    private Product product;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "variation")
//    @JsonSerialize(using = ProductVariationSerializer.class)
    @JsonManagedReference
    private List<ProductVariation> productVariationList;

}
