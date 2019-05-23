package com.example.demo.repositories;

import com.example.demo.models.Category;
import com.example.demo.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    List<Product> getProductsByCategory(Category category);
    Product getProductById(Long id);

    @Query("select p from Product p where lower(p.productName) like lower(concat('%', :nameToFind, '%'))")
    List<Product> findByNameFree(@Param("nameToFind")String nameToFind);
}