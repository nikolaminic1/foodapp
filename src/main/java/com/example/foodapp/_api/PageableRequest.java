package com.example.foodapp._api;

import lombok.Data;

@Data
public class PageableRequest {
    private Integer page;
    private Integer limit;
    private String order;

    public PageableRequest(Integer page, Integer limit, String order) {
        this.page = page;
        this.limit = limit;
        this.order = order;
    }
}
