package com.categoryservices.service;

import com.categoryservices.model.Products;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductClient {

    @GetMapping("product/category/{categoryId}")
    public List<Products> getProducts(@PathVariable("categoryId") Long categoryId);

}
