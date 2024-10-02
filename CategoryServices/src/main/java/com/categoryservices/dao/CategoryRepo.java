package com.categoryservices.dao;

import com.categoryservices.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {

    @Query("SELECT new com.categoryservices.payload.CategoryDto(c.name) from Category c")
    public List<Category> findAllNames();



}
