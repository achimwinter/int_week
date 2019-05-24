package com.example.demo.controllers;

import com.example.demo.models.Category;
import com.example.demo.models.Order;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/cart")
    public String getCart(Model model, @AuthenticationPrincipal User user) {
        OrderList cart = cartService.getOrCreateOrderList(user);
        model.addAttribute("cart", cart);

        return "cart";
    }

    @GetMapping("/checkout")
    public String getCheckout(@AuthenticationPrincipal User user) {
        cartService.checkout(cartService.getOrCreateOrderList(user));
        return "checkout";
    }

    @PostMapping("/api/cart/order")
    public RedirectView putInCart(
        @AuthenticationPrincipal User user,
        @RequestParam Map<String, String> params,
        HttpServletRequest request
    ) {
        Long article = Long.parseLong(params.get("articleid"));
        long amount = 1L;
        if (params.get("amount") != null || params.get("amount").equals("")) {
            amount = Long.parseLong(params.get("amount"));
        }

        Product product = productService.getByID(article);

        OrderList activecart = cartService.getOrCreateOrderList(user);
        cartService.changeOrder(activecart, product, amount);

        return new RedirectView(request.getHeader("Referer"));
    }

    @GetMapping("/article/{id}")
    public String getSingleArticle(Model model, @PathVariable long id, @AuthenticationPrincipal User user) {
        Product article = productService.getByID(id);
        model.addAttribute("article", article);

        List<Category> allCategories = categoryService.getCategories();
        model.addAttribute("categories", allCategories);

        long amount = 0;
        if (user != null) {
            OrderList cart = cartService.getOrCreateOrderList(user);
            Set<Order> orders = cart.getOrders();
            Order cartorder = orders.stream().filter(x -> x.getProduct().equals(article)).findAny().orElse(null);
            if (cartorder != null)
                amount = cartorder.getAmount();
        }

        model.addAttribute("amount", amount);

        long userId;
        if (user == null)
            userId = -1L;
        else
            userId = user.getId();
        model.addAttribute("activeuserid", userId);

        return "article";
    }

    @DeleteMapping("/api/cart/order")
    public RedirectView deleteInCart(
        @AuthenticationPrincipal User user,
        @RequestParam Map<String, String> params,
        HttpServletRequest request
    ) {
        Long article = Long.parseLong(params.get("articleid"));

        Product product = productService.getByID(article);

        OrderList activecart = cartService.getOrCreateOrderList(user);
        cartService.deleteOrder(activecart, product);

        return new RedirectView(request.getHeader("Referer"));
    }
}
