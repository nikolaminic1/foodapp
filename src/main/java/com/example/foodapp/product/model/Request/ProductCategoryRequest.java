package com.example.foodapp.product.model.Request;

public class ProductCategoryRequest {
    private String nameOfCategory;
    private String descOfCategory;
    private boolean isFeatured;
    private boolean isVisible;

    public String getNameOfCategory() {
        return nameOfCategory;
    }

    public String getDescOfCategory() {
        return descOfCategory;
    }

    public boolean isFeatured() {
        return isFeatured;
    }

    public boolean isVisible() {
        return isVisible;
    }
}
