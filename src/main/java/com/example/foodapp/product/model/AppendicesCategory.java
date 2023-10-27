package com.example.foodapp.product.model;

import com.example.foodapp.business.serializers.View;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"appendicesList"})
@EqualsAndHashCode(exclude = {"appendicesList"})
public class AppendicesCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.Public.class)
    private Long id;
    @JsonView(View.Public.class)
    private String nameOfCategory;
    @JsonView(View.Public.class)
    private Boolean isRequired;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "appendicesCategory", fetch = FetchType.LAZY)
    @JsonManagedReference
    @JsonView(View.Public.class)
    private List<Appendices> appendicesList;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JsonBackReference
    @JsonView(View.Public.class)
    private Product product;

    @OneToOne(cascade = CascadeType.MERGE)
    @JsonView(View.Public.class)
    private Image image;

    @JsonView(View.Public.class)
    private int numberOfAllowed;

    public void setAppendicesList (Appendices appendices) {
        if (this.getAppendicesList() == null){
            this.appendicesList = new ArrayList<>();
            this.appendicesList.add(appendices);
        } else {
            this.getAppendicesList().add(appendices);

        }

    }
}


