package com.example.foodapp.order.model;

import com.example.foodapp.product.model.Appendices;
import com.example.foodapp.product.model.AppendicesCategory;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"orderProducts"})
@EqualsAndHashCode(exclude = {"orderProducts"})
public class AppendicesCategoryOrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.MERGE)
    private List<Appendices> appendicesList;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private AppendicesCategory appendicesCategory;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
//    @JoinTable(name = "order_products_app_cat_table")
    @JsonBackReference
    private OrderProduct orderProducts;

    public void setOrderProduct(OrderProduct orderProduct){
        this.orderProducts = orderProduct;
        orderProduct.getAppendicesCategoryList().add(this);
    }

    public double getCategoryTotal() {
        double total = 0.0;
        if (this.appendicesList != null &&
            this.appendicesList.size() > 0) {
            for (Appendices side_dish: this.appendicesList) {
                if (side_dish.getDoesAffectPrice()) {
                    total += side_dish.getPrice();
                }
            }
        }
        return total;
    }

    public void addAppendicesToList(Appendices appendices){
        System.out.println(this.appendicesList);
        this.appendicesList.add(appendices);
        System.out.println(this.appendicesList);
    }
}
