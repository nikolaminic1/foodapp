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
    @JsonView({View.PublicList.class})
    private Long id;

    @JsonView({View.PublicList.class})
    private String name;
    @JsonView({View.PublicList.class})
    private Status status;
    @JsonView({View.PublicList.class})
    private String description;
    @JsonView({View.PublicList.class})
    private String backgroundImage;
    @JsonView({View.PublicList.class})
    private String logoImage;
    @JsonView({View.PublicList.class})
    private double priceOfDelivery;
    @JsonView({View.PublicList.class})
    private double priceOfOrderForFreeDelivery;

    @JsonIgnore
    private boolean isActive;
    @OneToMany(cascade = CascadeType.MERGE
            , fetch = FetchType.EAGER
            , mappedBy = "business"
    )
//    @JsonSerialize(using = ProductCategory_BusinessSerializer.class)
    @JsonManagedReference
    @JsonView({View.PublicDetail.class})
    private List<ProductCategory> productCategories;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JsonView({View.PublicList.class})
    private TimeOpenedWeek timeOpened;

    @OneToOne(fetch = FetchType.LAZY
            , cascade = CascadeType.MERGE
            , mappedBy = "business"
    )
    @JsonManagedReference
    @JsonIgnore
    private BusinessOwner businessOwner;

    @OneToMany
    @JsonView({View.PublicList.class})
    private List<BusinessTag> tags;

    @OneToOne(mappedBy = "business")
    @JsonManagedReference
    @JsonView({View.PublicList.class})
    private BusinessLocation businessLocation;

    @JsonView({View.PublicList.class})
    private double averageRating;

    @JsonView({View.PublicList.class})
    public TimeOpenedWeek getTimeOpened() {
        return this.timeOpened;
    }
    public void addBusinessCategory(ProductCategory productCategory) {
        this.productCategories.add(productCategory);
        productCategory.setBusiness(this);
    }

    @JsonView(View.Public.class)
//    @JsonIgnore
    public List<Object> getWorkingTime() {
        var monday = this.timeOpened.getWorkingTimeMonday();
        monday.put("day", "monday");
        var tuesday = this.timeOpened.getWorkingTimeTuesday();
        tuesday.put("day", "tuesday");
        var wednesday = this.timeOpened.getWorkingTimeWednesday();
        wednesday.put("day", "wednesday");
        var thursday = this.timeOpened.getWorkingTimeThursday();
        thursday.put("day", "thursday");
        var friday = this.timeOpened.getWorkingTimeFriday();
        friday.put("day", "friday");
        var saturday = this.timeOpened.getWorkingTimeSaturday();
        saturday.put("day", "saturday");
        var sunday = this.timeOpened.getWorkingTimeSunday();
        sunday.put("day", "sunday");

        return List.of(monday, tuesday, wednesday, thursday, friday, saturday, sunday);
    }

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
