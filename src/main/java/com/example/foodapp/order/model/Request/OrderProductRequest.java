package com.example.foodapp.order.model.Request;


import java.util.List;
import java.util.Map;

public class OrderProductRequest {
    private Long productId;
    private Long productVariationId;
//    private Map<Long, List<Long>> appendicesCategoryList;
    private int quantity;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductVariationId() {
        return productVariationId;
    }

    public void setProductVariationId(Long productVariationId) {
        this.productVariationId = productVariationId;
    }

//    public Map<Long, List<Long>> getAppendicesCategoryList() {
//        return appendicesCategoryList;
//    }
//
//    public void setAppendicesCategoryList(Map<Long, List<Long>> appendicesCategoryList) {
//        this.appendicesCategoryList = appendicesCategoryList;
//    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
