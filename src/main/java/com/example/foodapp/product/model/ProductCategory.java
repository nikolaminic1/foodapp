package com.example.foodapp.product.model;

//import com.example.foodapp.business.model.Business;
import com.example.foodapp.business.model.Business;
import com.example.foodapp.business.serializers.View;
import com.example.foodapp.product.serializers.ProductCategory_BusinessSerializer;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
//@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"business"})
@EqualsAndHashCode(exclude = {"business"})
//@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "business" })
//@EqualsAndHashCode(exclude = { "business" })
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nameOfCategory;
    private String descOfCategory;
    private Boolean categoryVisible;
    private Boolean featured;

    @CreationTimestamp
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    private LocalDateTime dateUpdated;
    private URI uri;

//    @OneToOne(cascade = CascadeType.MERGE, mappedBy = "product")
//    @JoinColumn(name = "image_id", referencedColumnName = "id")
//    private ProductImage productImage;

    @ManyToOne(cascade = CascadeType.MERGE
            , fetch = FetchType.LAZY)
//    @JoinColumn(name = "business_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonSerialize(using = ProductCategory_BusinessSerializer.class)
    @JsonBackReference
    @JsonIgnore
    private Business business;

    @OneToMany(cascade = CascadeType.MERGE
            , fetch = FetchType.LAZY
            , mappedBy = "productCategory"
    )
//    @JoinColumn(name = "product_fk", referencedColumnName = "id")
    @JsonManagedReference
    private List<Product> productList;
//
//    @JsonManagedReference
//    public List<Product> getProductList(){
//        return productList;
//    }

//    @JsonBackReference
//    public Business getBusiness() {
//        System.out.println("business");
//        return business;
//    }

    public Object getAdminDetail() {
        Map<String, Object> detail = new HashMap<>();
        detail.put("id", this.getId());
        detail.put("nameOfCategory", this.getNameOfCategory());
        detail.put("descOfCategory", this.getDescOfCategory());
        detail.put("categoryVisible", this.getCategoryVisible());
        detail.put("featured", this.getFeatured());
        detail.put("dateCreated", this.getDateCreated().format(DateTimeFormatter.ISO_DATE_TIME));
        detail.put("dateUpdated", this.getDateUpdated().format(DateTimeFormatter.ISO_DATE_TIME));
        return detail;
    }
}
