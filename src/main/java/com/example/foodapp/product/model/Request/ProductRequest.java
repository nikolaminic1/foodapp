package com.example.foodapp.product.model.Request;


import com.example.foodapp.product.enumeration.Availability;

public class ProductRequest {
    private String codeOfProduct;
    private double priceOfProduct;
    private double discountPrice;
    private boolean isOnDiscount;
    private String aboutProduct;
    private int preparationTime;
    private Availability availability;
    private boolean productVisible;

    public String getCodeOfProduct() {
        return codeOfProduct;
    }

    public void setCodeOfProduct(String codeOfProduct) {
        this.codeOfProduct = codeOfProduct;
    }

    public double getPriceOfProduct() {
        return priceOfProduct;
    }

    public void setPriceOfProduct(double priceOfProduct) {
        this.priceOfProduct = priceOfProduct;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public boolean isOnDiscount() {
        return isOnDiscount;
    }

    public void setOnDiscount(boolean onDiscount) {
        isOnDiscount = onDiscount;
    }

    public String getAboutProduct() {
        return aboutProduct;
    }

    public void setAboutProduct(String aboutProduct) {
        this.aboutProduct = aboutProduct;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    public boolean isProductVisible() {
        return productVisible;
    }

    public void setProductVisible(boolean productVisible) {
        this.productVisible = productVisible;
    }

    public long getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(long productCategory) {
        this.productCategory = productCategory;
    }

    private long productCategory;
}
