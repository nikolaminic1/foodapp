package com.example.foodapp.business.model;

import com.example.foodapp.auth.user.BusinessOwner;
import com.example.foodapp.business.enumeration.Status;
import com.example.foodapp.product.model.ProductCategory;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import jakarta.persistence.*;

import java.util.List;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Getter
@Setter
//@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
//@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
//@EqualsAndHashCode(exclude = { "productCategories" })
public class Business {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String name;
    private Status status;
    private String description;
    private String backgroundImage;
    private String logoImage;
    private boolean isActive;
    private double priceOfDelivery;
    private double priceOfOrderForFreeDelivery;

    @OneToMany(cascade = CascadeType.ALL
            , fetch = FetchType.LAZY
            , mappedBy = "business"
    )
//    @JsonSerialize(using = ProductCategory_BusinessSerializer.class)
    @JsonManagedReference
    private List<ProductCategory> productCategories;

    @OneToOne(cascade = CascadeType.ALL)
    private TimeOpenedWeek timeOpened;

    @OneToOne(fetch = FetchType.LAZY
            , cascade = CascadeType.ALL
            , mappedBy = "business"
    )
    @JsonManagedReference
    private BusinessOwner businessOwner;

    @OneToOne(mappedBy = "business")
    @JsonManagedReference
    private BusinessLocation businessLocation;

    private double averageRating;

    public void addBusinessCategory(ProductCategory productCategory) {
        this.productCategories.add(productCategory);
        productCategory.setBusiness(this);
    }

//    @JsonManagedReference
//    public List<ProductCategory> getProductCategories() {
//        System.out.println("product categories");
//        return productCategories;
//    }
}
