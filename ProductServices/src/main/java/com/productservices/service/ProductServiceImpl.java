package com.productservices.service;

import com.productservices.dao.ProductRepo;
import com.productservices.model.Products;
import com.productservices.payload.ProductDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepo productRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<ProductDto> findProducts(String name) {

        List<Products> productsList = productRepo.findByNameContaining(name);

        return productsList
                .stream().map((product)->this.modelMapper.map(product, ProductDto.class)).toList();
    }


    @Override
    public List<ProductDto> findProductsPage(String name,int pageNumber,int pageSize) {

        Page<Products> productsPage = productRepo.findByNameContaining(name, PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC,"id"));
        return productsPage.
                stream().map((product)->(this.modelMapper.map(product,ProductDto.class))).collect(Collectors.toList());
    }


    @Override
    public List<Products> findProductsByCategory(Long categoryId) {

        return productRepo.findByCategoryId(categoryId);
    }

    @Override
    public Products findProductsById(Long productId) {

        return productRepo.findById(productId).orElseThrow(()->new ResolutionException("No product found for this product id"));
    }
}
