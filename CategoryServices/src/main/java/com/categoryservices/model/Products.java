package com.categoryservices.model;

import lombok.Data;

@Data
public class Products {

    private Long id;
    private String name;
    private String description;
    private double price;
    private int stockQuantity;
    private Long categoryId;
}
