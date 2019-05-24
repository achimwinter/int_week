package com.example.demo.repositories;

import com.example.demo.models.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category getCategoryByName(String name);

    @Query("select c from Category c where lower(c.name) like lower(concat('%', :nameToFind, '%'))")
    List<Category> findByNameFree(@Param("nameToFind") String nameToFind);

}