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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Data
@ToString(exclude = {"businessOwner", "businessLocation"})
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "businessOwner", "businessLocation" })
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
            , fetch = FetchType.EAGER
            , mappedBy = "business"
    )
//    @JsonSerialize(using = ProductCategory_BusinessSerializer.class)
    @JsonManagedReference
    private List<ProductCategory> productCategories;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
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

    public TimeOpenedWeek getTimeOpened() {
        return this.timeOpened;
    }
    public void addBusinessCategory(ProductCategory productCategory) {
        this.productCategories.add(productCategory);
        productCategory.setBusiness(this);
    }

//    @JsonIgnore
//    public Map<String, Object> getWorkingTime() {
//        Map<String, Object> map = new HashMap<>();
//        map.put("monday", this.timeOpened.getWorkingTimeMonday());
//        map.put("tuesday", this.timeOpened.getWorkingTimeTuesday());
//        map.put("wednesday", this.timeOpened.getWorkingTimeWednesday());
//        map.put("thursday", this.timeOpened.getWorkingTimeThursday());
//        map.put("friday", this.timeOpened.getWorkingTimeFriday());
//        map.put("saturday", this.timeOpened.getWorkingTimeSaturday());
//        map.put("sunday", this.timeOpened.getWorkingTimeSunday());
//        return map;
//    }

//    @JsonView({View.Internal.class})
//    public Map<String, String> getBusinessOwner() {
//        var map = new HashMap<String, String>();
//        map.put("tax_code", this.businessOwner.getTaxCode());
//        return map;
//    }

//    @JsonManagedReference
//    public List<ProductCategory> getProductCategories() {
//        System.out.println("product categories");
//        return productCategories;
//    }
}
