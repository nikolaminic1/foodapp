package com.example.foodapp._api;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class PaginatedResponse<T> {
    List<T> items;
    String next;
    String previous;
    Long count;
}
