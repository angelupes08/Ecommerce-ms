package com.categoryservices.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto implements Serializable {

    private String name;

    private String description;

    //private List<ProductDto> productDtos;

    public CategoryDto(String name){
        this.name=name;
    }
}
