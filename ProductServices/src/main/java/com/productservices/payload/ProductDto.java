package com.productservices.payload;

import jakarta.persistence.Column;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProductDto implements Serializable {

    private String name;

    private String description;

    private double price;

    private int stockQuantity;

}
