package com.example.foodapp.product.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import jakarta.persistence.*;

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
    private Long id;
    private String nameOfCategory;
    private Boolean isRequired;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "appendicesCategory", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Appendices> appendicesList;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private Product product;

    @OneToOne(cascade = CascadeType.ALL)
    private Image image;

    private int numberOfAllowed;
}
