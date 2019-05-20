package com.example.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserController {

    @RequestMapping(value = "/login/{userId}", method = RequestMethod.POST)
    public ResponseEntity<String> login(@PathVariable long userId) {
        return new ResponseEntity<>("User " + userId + " logged in successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/logout/{userId}", method = RequestMethod.POST)
    public ResponseEntity<String> logout(@PathVariable long userId) {
        return new ResponseEntity<>("User " + userId + " logged out", HttpStatus.OK);
    }

}
