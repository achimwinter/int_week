package com.example.demo.services;

import com.example.demo.models.Category;
import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> list() {
        return productRepository.findAll();
    }

    public List<Product> getProductsForCategory(Category category) {
        return productRepository.getProductsByCategory(category);
    }

    public Product getByID(Long id) {
        return productRepository.getProductById(id);
    }
}
