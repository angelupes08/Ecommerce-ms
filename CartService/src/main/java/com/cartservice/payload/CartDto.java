package com.cartservice.payload;

import com.cartservice.model.Products;
import lombok.Data;

@Data
public class CartDto {

    private Products productDto;

    private int quantity;

    private double price;


}
