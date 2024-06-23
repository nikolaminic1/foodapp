package com.example.foodapp._api;

import lombok.Data;

import java.util.List;

@Data
public class PaginatedResponseSerialized<T> {
    List<T> items;
    String next;
    String previous;
    Long count;
}
