package com.example.foodapp.product.model.Request;

public class ProductVariationRequest {
    private String name;
    private String value;
    private String codeOfVariation;
    private Boolean doesAffectPrice;
    private double priceOfVariation;
    private double priceOfVariationDiscount;
    private Boolean isOnDiscount;
    private long variationId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCodeOfVariation() {
        return codeOfVariation;
    }

    public void setCodeOfVariation(String codeOfVariation) {
        this.codeOfVariation = codeOfVariation;
    }

    public Boolean getDoesAffectPrice() {
        return doesAffectPrice;
    }

    public void setDoesAffectPrice(Boolean doesAffectPrice) {
        this.doesAffectPrice = doesAffectPrice;
    }

    public double getPriceOfVariation() {
        return priceOfVariation;
    }

    public void setPriceOfVariation(double priceOfVariation) {
        this.priceOfVariation = priceOfVariation;
    }

    public double getPriceOfVariationDiscount() {
        return priceOfVariationDiscount;
    }

    public void setPriceOfVariationDiscount(double priceOfVariationDiscount) {
        this.priceOfVariationDiscount = priceOfVariationDiscount;
    }

    public Boolean getOnDiscount() {
        return isOnDiscount;
    }

    public void setOnDiscount(Boolean onDiscount) {
        isOnDiscount = onDiscount;
    }

    public long getVariationId() {
        return variationId;
    }

    public void setVariationId(long variationId) {
        this.variationId = variationId;
    }
}
