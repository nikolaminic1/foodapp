package com.example.foodapp.product.model;

import com.example.foodapp.business.serializers.View;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import jakarta.persistence.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@ToString(exclude = {"appendicesList"})
//@EqualsAndHashCode(exclude = {"appendicesList"})
//@Transactional
public class AppendicesCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nameOfCategory;
    private Boolean isRequired;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "appendicesCategory", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Appendices> appendicesList = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JsonBackReference
    private Product product;

    @OneToOne(cascade = CascadeType.MERGE)
    private Image image;

    private int numberOfAllowed;

    public void setProduct (Product product) {
        this.product = product;
//        product.getAppendicesCategoryList().add(this);
    }

    public void setAppendicesList (Appendices appendices) {
        if (this.getAppendicesList() == null){
            this.appendicesList = new ArrayList<>();
            this.appendicesList.add(appendices);
        } else {
            this.getAppendicesList().add(appendices);

        }

    }

    public Map<String, Object> getAdminSideDishCategoryDetail() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", this.getId());
        map.put("nameOfCategory", this.getId());
        map.put("isRequired", this.getId());
        map.put("product", this.getId());

        if (this.getImage() != null) {
            map.put("image", this.getImage().getImageDetail());
        }

        map.put("numberOfAllowed", this.getNumberOfAllowed());

        return map;
    }
}


