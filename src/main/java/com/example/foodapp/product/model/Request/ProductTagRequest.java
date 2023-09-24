package com.example.foodapp.product.model.Request;

import java.util.List;
import java.util.Map;

public class ProductTagRequest {
    private Long productId;
    private List<String> tagList;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }
}
