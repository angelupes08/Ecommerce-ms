package com.cartservice.model;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class Products {

    private Long id;

    private String name;

    private String description;

    private double price;

    private int stockQuantity;
}
