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

    private String token;


    @GetMapping("")
    public ResponseEntity<List<Category>> getCategories(@RequestHeader("Authorization")String authHeader) throws UnauthorizedException{

        this.validateToken(authHeader);

        return new ResponseEntity<>(categoryService.findCategories(token),HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@RequestHeader("Authorization")String authHeader,
                                                    @PathVariable("categoryId")Long categoryId) throws UnauthorizedException {

        this.validateToken(authHeader);

        return new ResponseEntity<>(categoryService.findCategoryById(categoryId),HttpStatus.OK);
    }

    public void validateToken(String authHeader) throws UnauthorizedException {

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);

        }
        else{
            throw new UnauthorizedException("Access Denied");
        }

        try{
            Boolean isValid = userClient.validateToken(token);

            if(!isValid){

                throw new UnauthorizedException("Access Denied");

            }
        }

        catch (FeignException e) {

            throw new UnauthorizedException("Token validation failed");
        }
    }


}
