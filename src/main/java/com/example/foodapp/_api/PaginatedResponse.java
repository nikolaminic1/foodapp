package com.example.foodapp._api;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class PaginatedResponse<T> {
    List<T> items;
    Long count;
    Integer pages;
    Integer size;
    Integer page;

    public PaginatedResponse(Page<T> items) {
        System.out.println(items.getContent());
        this.items = items.getContent();
        this.count = items.getTotalElements();
        this.pages = items.getTotalPages();
        this.size = items.getSize();
        this.page = items.getNumber();
    }
}
