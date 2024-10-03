package com.categoryservices.controller;

import com.categoryservices.exception.UnauthorizedException;
import com.categoryservices.model.Category;
import com.categoryservices.payload.CategoryDto;
import com.categoryservices.service.CategoryService;
import com.categoryservices.service.UserClient;
import feign.FeignException;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserClient userClient;


    @GetMapping("")
    public ResponseEntity<List<Category>> getCategories() throws UnauthorizedException{

        return new ResponseEntity<>(categoryService.findCategories(),HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("categoryId")Long categoryId) throws UnauthorizedException {

        return new ResponseEntity<>(categoryService.findCategoryById(categoryId),HttpStatus.OK);
    }



}
