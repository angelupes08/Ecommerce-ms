package com.categoryservices.service;


import com.categoryservices.model.Category;
import com.categoryservices.payload.CategoryDto;

import java.util.List;

public interface CategoryService {

    public List<Category> findCategories(String token);

    public Category findCategoryById(Long categoryId);

}
