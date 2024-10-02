package com.productservices.controller;

import com.productservices.model.Products;
import com.productservices.payload.ProductDto;
import com.productservices.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;


    @Operation(summary = "Get List of Products By Name")
    @GetMapping("")
    public ResponseEntity<List<ProductDto>> getProductByName(@RequestParam(value = "name") String productName){

        return new ResponseEntity<>(productService.findProducts(productName), HttpStatus.OK);
    }

    @Operation(summary = "Get Products By Id")
    @GetMapping("/{productId}")
    public ResponseEntity<Products> getProductById(@PathVariable("productId")Long productId){

        return new ResponseEntity<>(productService.findProductsById(productId), HttpStatus.OK);
    }

    @Operation(summary = "Get List of Products By Name in a Page")
    @GetMapping("/page")
    public ResponseEntity<List<ProductDto>> getProductByNamePage(
            @RequestParam(value = "name") String productName,
            @RequestParam(value = "pageNo") int pageNo,
            @RequestParam(value = "pageSize",required = false,defaultValue = "2") int pageSize){

        return new ResponseEntity<>(productService.findProductsPage(productName,pageNo,pageSize), HttpStatus.OK);
    }


    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Products>> findProductByCategory(@PathVariable("categoryId")Long categoryId){

        return new ResponseEntity<>(productService.findProductsByCategory(categoryId),HttpStatus.OK);
    }
}
