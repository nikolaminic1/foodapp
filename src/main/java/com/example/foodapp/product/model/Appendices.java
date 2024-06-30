package com.example.foodapp.product.model;

import com.example.foodapp.business.serializers.View;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;

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

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JsonBackReference
//    @JoinTable(name = "appendicesListTable")
    private AppendicesCategory appendicesCategory;

    @OneToOne(cascade = CascadeType.MERGE)
    private Image image;

    public void setAppendicesCategory(AppendicesCategory appendicesCategory){
        this.appendicesCategory = appendicesCategory;
        appendicesCategory.getAppendicesList().add(this);
    }

    public Map<String, Object> getDetail() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", this.getId());
        map.put("nameOfSideDish", getNameOfAppendices());
        map.put("doesAffectPrice", getDoesAffectPrice());
        map.put("price", getPrice());
        return map;
    }

}
