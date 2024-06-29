package com.example.foodapp._api;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class PaginatedResponse<T> {
    List<T> items;
    String next;
    String previous;
    Long count;

    public PaginatedResponse(Page<T> items) {
        System.out.println(items.getContent());
        this.items = items.getContent();

    }
}
