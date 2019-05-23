package com.example.demo.controllers;

import com.example.demo.models.Order;
import com.example.demo.models.OrderList;
import com.example.demo.models.Product;
import com.example.demo.models.User;
import com.example.demo.services.CartService;

import com.example.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/cart1")
//TODO URL aendern
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;



    @GetMapping(value = "/cart1")
    public String getCart(User user, Model model) {
        model.addAttribute("products", cartService.getActiveOrderList(user));
        // TODO:: Place your HTML Filename here
        return "test";
    }

    @GetMapping(value = "/orderlist")
    public String getCompletedOrders(User user, Model model) {
        model.addAttribute("completedOrders", cartService.getCompletedOrders(user));
        // TODO:: Place your HTML Filename here
        return "";
    }

    @PostMapping("/order")
    public RedirectView putInCart(@AuthenticationPrincipal User user, @RequestParam Map<String, String> params, HttpServletRequest request) {
        Long article = Long.parseLong(params.get("articleid"));
        long amount = 1L;
        if (params.get("amount") != null) {
            amount = Long.parseLong(params.get("amount"));
        }

        Product product = productService.getByID(article);

        OrderList activecart = cartService.getOrCreateOrderList(user);
        cartService.changeOrder(activecart, product, amount);

        return new RedirectView(request.getHeader("Referer"));
    }

    @DeleteMapping("/order")
    public RedirectView deleteInCart(@AuthenticationPrincipal User user, @RequestParam Map<String, String> params, HttpServletRequest request) {
        Long article = Long.parseLong(params.get("articleid"));

        Product product = productService.getByID(article);

        OrderList activecart = cartService.getOrCreateOrderList(user);
        cartService.deleteOrder(activecart, product);

        return new RedirectView(request.getHeader("Referer"));
    }

    @PutMapping
    public Object addProduct() {
        return null;
    }

    @DeleteMapping
    public Object removeProduct() {
        return null;
    }

    @PutMapping(value = "/product/amount")
    public ResponseEntity changeProductAmount() {
        return ResponseEntity.ok().build();
    }
}
