package com.example.demo.controllers;

import com.example.demo.models.Category;
import com.example.demo.models.Product;
import com.example.demo.services.CategoryService;
import com.example.demo.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/products")
    public String getProducts(Model model, @Nullable @RequestParam(value = "searchString") final String searchString) {
        List<Product> products;
        if (searchString == null) {
            products = productService.list();
        } else {
            products = productService.search(searchString);
        }
        model.addAttribute("prods", products);
        List<Category> allCategories = categoryService.getCategories();
        model.addAttribute("categories", allCategories);

        return "shop";
    }

    @GetMapping("/products/{category}")
    public String getCategory(Model model, @PathVariable String category) {
        Category cat = categoryService.getCategory(category);
        List<Product> categoryProducts = productService.getProductsForCategory(cat);
        model.addAttribute("prods", categoryProducts);
        List<Category> allCategories = categoryService.getCategories();
        model.addAttribute("categories", allCategories);

        return "shop";
    }



}
