package com.example.demo.services;

import com.example.demo.models.Category;
import com.example.demo.models.OrderList;
import com.example.demo.models.Product;
import com.example.demo.models.User;
import com.example.demo.repositories.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategory(String s) {
        return categoryRepository.getCategoryByName(s);
    }
}
