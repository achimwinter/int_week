package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.services.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart1")
//TODO URL aendern
public class CartController {

    @Autowired
    private CartService cartService;

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
