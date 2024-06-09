package com.example.foodapp.product.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"product"})
@EqualsAndHashCode(exclude = {"product"})
public class ProductTag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne
//    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;

//    public void setProduct(Product product) {
//        this.product = product;
//        product.getProductTags().add(this);
//    }
//
//    public Map<String, Object> getProductTagDetail() {
//        Map<String, Object> map = new HashMap<>();
//        map.put("id", this.getId());
//        map.put("name", this.getName());
//        return map;
//    }
}
