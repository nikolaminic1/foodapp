package com.example.foodapp.product.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import jakarta.persistence.*;
import org.aspectj.weaver.ast.Var;

import java.util.ArrayList;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"variation"})
@EqualsAndHashCode(exclude = {"variation"})
public class ProductVariation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String value;
    private String codeOfVariation;
    private Boolean doesAffectPrice;
    private double priceOfVariation;
    private double priceOfVariationDiscount;
    private double totalPrice;
    private Boolean isOnDiscount;

    @ManyToOne
//    @JoinColumn(name = "variation_id")
    @JsonBackReference
    @JsonIgnore
    private Variation variation;

    @OneToOne(cascade = CascadeType.MERGE)
//    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private ProductImage productImage;

    public void setPrice(){
        if(doesAffectPrice & !isOnDiscount){
            this.totalPrice = priceOfVariation;
        } else if(doesAffectPrice & isOnDiscount) {
            this.totalPrice = priceOfVariationDiscount;
        }
    }

    public void setVariation(Variation variation) {
        this.variation = variation;
        variation.getProductVariationList().add(this);
    }

    public Double getPrice(){
        if(isOnDiscount == null){
            return null;
        }
        if(doesAffectPrice & !isOnDiscount){
            return priceOfVariation;
        } else if(doesAffectPrice & isOnDiscount) {
            return priceOfVariationDiscount;
        } else {
            return null;
        }
    }
}
