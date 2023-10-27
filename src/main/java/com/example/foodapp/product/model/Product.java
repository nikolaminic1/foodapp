package com.example.foodapp.product.model;

import com.example.foodapp.business.serializers.View;
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
import java.util.ArrayList;
import java.util.Collection;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.Public.class)
    private Long id;
    @JsonView(View.Public.class)
    private String nameOfProduct;
    @JsonView(View.Public.class)
    private String codeOfProduct;
    @JsonView(View.Public.class)
    private double priceOfProduct;
    @JsonView(View.Public.class)
    private double discountPrice;
    @JsonView(View.Public.class)
    private double discountPercentage;
    @JsonView(View.Public.class)
    private Boolean isOnDiscount;
    @JsonView(View.Public.class)
    private String aboutProduct;
    @JsonView(View.Public.class)
    private int preparationTime;
    @JsonView(View.Public.class)
    private Availability availability;
    @JsonView(View.Public.class)
    private int weight;

    @CreationTimestamp
    @JsonView(View.Public.class)
    private LocalDateTime dataCreated;

    @UpdateTimestamp
    @JsonView(View.Public.class)
    private LocalDateTime dateUpdated;
    @JsonView(View.Public.class)
    private Boolean productVisible;

    @JsonView(View.Public.class)
    private URI uri;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JsonSerialize(using = ProductCategorySerializer.class)
//    @JoinColumn(name = "product_category")
    @JsonBackReference
    @JsonView(View.Public.class)
    private ProductCategory productCategory;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE
//            , mappedBy = "product"
    )
//    @JsonBackReference
    @JsonView(View.Public.class)
    private ProductImage productImage;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, mappedBy = "product")
//    @JsonSerialize(using = VariationSerializer.class)
    @JsonManagedReference
    @JsonView(View.Public.class)
    private Variation variations;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JsonManagedReference
    @JsonView(View.Public.class)
    private List<ProductTag> productTags = new ArrayList<>();

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "product", fetch = FetchType.LAZY)
    @JsonManagedReference
    @JsonView(View.Public.class)
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

    @OneToMany(mappedBy = "product")
    private Collection<ProductDescription> productDescription;

    public Collection<ProductDescription> getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(Collection<ProductDescription> productDescription) {
        this.productDescription = productDescription;
    }

    public void addToAppendicesCategoryList (AppendicesCategory appendicesCategory) {
        if (this.appendicesCategoryList == null){
            this.appendicesCategoryList = new ArrayList<>();
            this.appendicesCategoryList.add(appendicesCategory);
        } else {
            this.appendicesCategoryList.add(appendicesCategory);
        }
//        appendicesCategory.setProduct(this);
    }
}
