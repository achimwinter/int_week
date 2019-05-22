package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart1")
//TODO URL aendern
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/cart1", method = RequestMethod.GET)
    public String getCart(User user, Model model) {
        model.addAttribute("products", cartService.getActiveOrderList(user));
        // TODO:: Place your HTML Filename here
        return "test";
    }

    @RequestMapping(value = "/orderlist", method = RequestMethod.GET)
    public String getCompletedOrders(User user, Model model) {
        model.addAttribute("completedOrders", cartService.getCompletedOrders(user));
        // TODO:: Place your HTML Filename here
        return "";
    }


    @RequestMapping(method = RequestMethod.PUT)
    public Object addProduct() {
        return null;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public Object removeProduct() {
        return null;
    }

    @RequestMapping(value = "/product/amount", method = RequestMethod.PUT)
    public ResponseEntity changeProductAmount() {
        return ResponseEntity.ok().build();
    }
}
