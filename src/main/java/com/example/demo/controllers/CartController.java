package com.example.demo.controllers;

import com.example.demo.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @RequestMapping(method = RequestMethod.GET)
    public String getCart() {
        return "string";
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
