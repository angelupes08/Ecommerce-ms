package com.productservices.service;

import com.productservices.model.Products;
import com.productservices.payload.ProductDto;

import java.util.List;

public interface ProductService {

    public List<ProductDto> findProducts(String name);

    public List<ProductDto> findProductsPage(String name,int pageNumber,int pageSize);

    public List<Products> findProductsByCategory(Long categoryId);

    public Products findProductsById(Long productId);
}
