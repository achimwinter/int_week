package com.example.demo.controllers;

import com.example.demo.models.Category;
import com.example.demo.models.OrderList;
import com.example.demo.models.Product;
import com.example.demo.models.User;
import com.example.demo.services.CartService;
import com.example.demo.services.CategoryService;
import com.example.demo.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class WelcomeController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    CartService cartService;

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
    public String getCart(Model model, @AuthenticationPrincipal User user) {
        OrderList cart = cartService.getActiveOrderList(user);
        model.addAttribute("cart", cart);

        return "cart";
    }

    @GetMapping("/profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("activeuser", user);

        return "profile";
    }

    @GetMapping("/article/{id}")
    public String getSingleArticle(Model model, @PathVariable long id, @AuthenticationPrincipal User user) {
        Product article = productService.getByID(id);
        model.addAttribute("article", article);

        List<Category> allCategories = categoryService.getCategories();
        model.addAttribute("categories", allCategories);

        long userId;
        if(user == null) userId = -1L;
        else userId = user.getId();
        model.addAttribute("activeuserid", userId);

        return "article";
    }

    @GetMapping("/hello")
    public String mainWithParam(
        @RequestParam(name = "name", required = false, defaultValue = "")
            String name, Model model
    ) {

        model.addAttribute("message", name);

        return "shop"; //view
    }

}