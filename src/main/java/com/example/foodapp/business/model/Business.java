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

    @OneToOne(cascade = CascadeType.MERGE)
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

    @JsonView({View.Internal.class})
    public Map<String, String> getBusinessOwner() {
        var map = new HashMap<String, String>();
        map.put("tax_code", this.businessOwner.getTaxCode());
        return map;
    }

//    @JsonManagedReference
//    public List<ProductCategory> getProductCategories() {
//        System.out.println("product categories");
//        return productCategories;
//    }
}
