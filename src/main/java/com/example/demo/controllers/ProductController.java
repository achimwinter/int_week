package com.example.demo.controllers;

import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Product> getAllProducts(@RequestParam @Nullable String category) {
        // TODO Pagination
        System.out.println("test");
        return productRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

}
