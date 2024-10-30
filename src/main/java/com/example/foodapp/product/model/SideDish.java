package com.example.foodapp.product.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"product"})
@EqualsAndHashCode(exclude = {"product"})
public class SideDish {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nameOfSideDish;
    private Boolean doesAffectPrice;
    private double price;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JsonBackReference
//    @JoinTable(name = "appendicesListTable")
    private Product product;

    @OneToOne(cascade = CascadeType.MERGE)
    private Image image;

    public Map<String, Object> getDetail() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", this.getId());
        map.put("nameOfSideDish", getNameOfSideDish());
        map.put("doesAffectPrice", getDoesAffectPrice());
        map.put("price", getPrice());
        return map;
    }

}
