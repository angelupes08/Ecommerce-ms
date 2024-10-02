package com.categoryservices.service;

import com.categoryservices.dao.CategoryRepo;
import com.categoryservices.exception.ResourceNotFoundException;
import com.categoryservices.model.Category;
import com.categoryservices.payload.CategoryDto;
import com.categoryservices.payload.Token;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ProductClient productClient;

    @Autowired
    UserClient userClient;

    @Override
    public List<Category> findCategories(String token) {

        List<Category> categories = categoryRepo.findAll();

        List<Category> categoryList =
        categories.stream().map(category->{category.setProducts(productClient.getProducts(category.getId()));
                return category;
                })
                .collect(Collectors.toList());
        return categoryList;
    }

    @Override
    public Category findCategoryById(Long categoryId) {

        Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("No category exists for this categoryId"));

        category.setProducts(productClient.getProducts(categoryId));

        return category;
    }



}
