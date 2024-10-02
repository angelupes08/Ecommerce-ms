package com.productservices.dao;


import com.productservices.model.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Products,Long> {

    public List<Products> findByNameContaining(String keyword);

    public Page<Products> findByNameContaining(String keyword, Pageable page);

    public List<Products> findByCategoryId(Long categoryId);
}
