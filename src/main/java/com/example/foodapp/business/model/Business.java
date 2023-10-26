package com.example.foodapp.business.model;

import com.example.foodapp.auth.user.UserProfiles.BusinessOwner;
import com.example.foodapp.business.enumeration.Status;
import com.example.foodapp.business.serializers.View;
import com.example.foodapp.product.model.ProductCategory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
//@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
//@EqualsAndHashCode(exclude = { "productCategories" })
public class Business {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Status status;
    private String description;
    private String backgroundImage;
    private String logoImage;
    private double priceOfDelivery;
    private double priceOfOrderForFreeDelivery;

    @JsonIgnore
    private boolean isActive;
    @OneToMany(cascade = CascadeType.MERGE
            , fetch = FetchType.LAZY
            , mappedBy = "business"
    )
//    @JsonSerialize(using = ProductCategory_BusinessSerializer.class)
    @JsonManagedReference
    private List<ProductCategory> productCategories;

    @OneToOne(cascade = CascadeType.MERGE)
    private TimeOpenedWeek timeOpened;

    @OneToOne(fetch = FetchType.LAZY
            , cascade = CascadeType.MERGE
            , mappedBy = "business"
    )
    @JsonManagedReference
    @JsonIgnore
    private BusinessOwner businessOwner;

    @OneToMany
    private List<BusinessTag> tags;

    @OneToOne(mappedBy = "business")
    @JsonManagedReference
    private BusinessLocation businessLocation;

    private double averageRating;

    public void addBusinessCategory(ProductCategory productCategory) {
        this.productCategories.add(productCategory);
        productCategory.setBusiness(this);
    }

//    @JsonView(View.Public.class)
    @JsonIgnore
    public Map<String, Object> getWorkingTime() {
        var map = new HashMap<String, Object>();
        map.put("monday", this.timeOpened.getWorkingTimeMonday());
        return map;
    }

//    @JsonManagedReference
//    public List<ProductCategory> getProductCategories() {
//        System.out.println("product categories");
//        return productCategories;
//    }
}
