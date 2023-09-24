package com.example.foodapp.product.model;

import com.example.foodapp.product.enumeration.Availability;
import com.example.foodapp.product.serializers.ProductCategorySerializer;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"productCategory", "appendicesCategoryList","productTags","variations"})
@EqualsAndHashCode(exclude = {"productCategory", "appendicesCategoryList", "productTags","variations"})
@Log4j2
//@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "productCategory" })
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.StringIdGenerator.class,
//        property="productCategory")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Product {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String nameOfProduct;
    private String codeOfProduct;
    private double priceOfProduct;
    private double discountPrice;
    private double discountPercentage;
    private Boolean isOnDiscount;
    private String aboutProduct;
    private int preparationTime;
    private Availability availability;

    @CreationTimestamp
    private LocalDateTime dataCreated;

    @UpdateTimestamp
    private LocalDateTime dateUpdated;

    private Boolean productVisible;
    private URI uri;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonSerialize(using = ProductCategorySerializer.class)
//    @JoinColumn(name = "product_category")
    @JsonBackReference
    private ProductCategory productCategory;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "product")
    @JsonBackReference
    private ProductImage productImage;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "product")
//    @JsonSerialize(using = VariationSerializer.class)
    @JsonManagedReference
    private Variation variations;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ProductTag> productTags;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<AppendicesCategory> appendicesCategoryList;

    public void setProductVisible(Boolean productVisible) {
        this.productVisible = productVisible;
    }

//    public void setProductImage(ProductImage productImage) {
//        this.productImage = productImage;
//        productImage.setProduct(this);
//    }
//
    @JsonManagedReference
    public Variation getVariation(){
        System.out.println("var");
        return this.variations;
    }

    @JsonBackReference
    public ProductCategory getProductCategory() {
        System.out.println("product cat");
        return productCategory;
    }
}
