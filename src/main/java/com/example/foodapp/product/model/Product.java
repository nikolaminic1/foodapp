package com.example.foodapp.product.model;

import com.example.foodapp.business.model.Business;
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
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.*;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"productCategory", "variation"})
@EqualsAndHashCode(exclude = {"productCategory", "variation"})
@Log4j2
//@Transactional
//@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "productCategory" })
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.StringIdGenerator.class,
//        property="productCategory")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    private int weight;

    @CreationTimestamp
    private LocalDateTime dataCreated;

    @UpdateTimestamp
    private LocalDateTime dateUpdated;
    private Boolean productVisible;

    private URI uri;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
//    @JsonSerialize(using = ProductCategorySerializer.class)
//    @JoinColumn(name = "product_category")
    @JsonBackReference
    private ProductCategory productCategory;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE
            , mappedBy = "product"
    )
    @JsonBackReference
    private ProductImage productImage;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, mappedBy = "product")
//    @JsonSerialize(using = VariationSerializer.class)
    @JsonManagedReference
    private Variation variation;

//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
//    @JsonManagedReference
//    private List<ProductTag> productTags = new ArrayList<>();

    @OneToMany(
//            cascade = CascadeType.MERGE,
            mappedBy = "product",
            fetch = FetchType.EAGER
    )
    @JsonManagedReference
    private List<AppendicesCategory> appendicesCategoryList = new ArrayList<>();

    public void setProductVisible(Boolean productVisible) {
        this.productVisible = productVisible;
    }

    public void setProductImage(ProductImage productImage) {
        this.productImage = productImage;
        productImage.setProduct(this);
    }

    @JsonManagedReference
    public Variation getVariation(){
        System.out.println("var");
        return this.variation;
    }

    //    public List<AppendicesCategory> getAppendicesCategoryList() {
    //        System.out.println(this.appendicesCategoryList);
    //        return this.appendicesCategoryList;
    //    }

    @OneToMany(mappedBy = "product")
    private Collection<ProductDescription> productDescription;

    public Collection<ProductDescription> getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(Collection<ProductDescription> productDescription) {
        this.productDescription = productDescription;
    }

    public void addToAppendicesCategoryList (AppendicesCategory appendicesCategory) {
//        this.appendicesCategoryList.add(appendicesCategory);
//        if (this.appendicesCategoryList == null){
//            this.appendicesCategoryList = new ArrayList<>();
//            this.appendicesCategoryList.add(appendicesCategory);
//        } else {
//            this.appendicesCategoryList.add(appendicesCategory);
//        }
//        appendicesCategory.setProduct(this);
    }

    public List<Map<String, Object>> getAllRelatedCategories() {
        List<Map<String, Object>> list = new ArrayList<>();
        Business business = this.getProductCategory().getBusiness();
        Collection<ProductCategory> categories = null;
        return list;
    }

    public Object getAdminProductCategoryDetail() {
        if (this.getProductCategory() != null) {
            return this.getProductCategory().getAdminDetail();
        }

        return null;
    }

    public List<Map<String, Object>> getProductDescriptionList() {
        List<Map<String, Object>> mapsList = new ArrayList<>();
        this.productDescription.forEach(desc -> mapsList.add(desc.getProductDescriptionData()));
        return mapsList;
    }

    public Object getAdminProductImageDetail() {
        if (this.getProductImage() != null) {
            return this.getProductImage().getAdminProductImageDetail();
        }

        return null;
    }
//    public List<Map<String, Object>> getAdminProductTagsList() {
//        List<Map<String, Object>> mapsList = new ArrayList<>();
//        this.getProductTags().forEach(tag -> mapsList.add(tag.getProductTagDetail()));
//        return mapsList;
//    }

    public List<Map<String, Object>> getAdminAppendixCategoryList() {
        List<Map<String, Object>> mapsList = new ArrayList<>();
        this.getAppendicesCategoryList().forEach(tag -> mapsList.add(tag.getAdminSideDishCategoryDetail()));
        return mapsList;
    }


}
