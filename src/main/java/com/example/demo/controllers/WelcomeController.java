package com.example.demo.controllers;

import com.example.demo.models.Category;
import com.example.demo.models.OrderList;
import com.example.demo.models.Product;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.services.CategoryService;
import com.example.demo.services.ProductService;
import com.google.common.collect.Lists;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Controller
public class WelcomeController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;


    @GetMapping("/")
    public RedirectView main(Model model) {
        return new RedirectView("products");
    }


    @GetMapping("/products")
    public String getProducts(Model model) {
        List<Product> allProducts = productService.list();
        model.addAttribute("prods", allProducts);
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


    @GetMapping("/cart")
    public String getCart(Model model) {
        //todo: get cart either by session OR parameter (bsp /hello) OR path variable (bsp /article/id) and add it to model
        OrderList testcart = new OrderList();
        model.addAttribute("cart", testcart);
        return "cart";
    }

    @GetMapping("/article/{id}")
    public String getSingleArticle(Model model, @PathVariable String id) {
        // todo: get single article and set into model attribute article
        Product testarticle = new Product();
        model.addAttribute("article", testarticle);
        return "article";
    }


    @GetMapping("/hello")
    public String mainWithParam(
            @RequestParam(name = "name", required = false, defaultValue = "")
                    String name, Model model) {

        model.addAttribute("message", name);

        return "shop"; //view
    }

}