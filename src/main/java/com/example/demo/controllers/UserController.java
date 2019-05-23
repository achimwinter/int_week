package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    //    @PostMapping(value = "/login")
    //    public ResponseEntity<String> login(@PathVariable long userId) {
    //        return new ResponseEntity<>("User " + userId + " logged in successfully", HttpStatus.OK);
    //    }
    //
    //    @PostMapping(value = "/logout")
    //    public ResponseEntity<String> logout(@PathVariable long userId) {
    //        return new ResponseEntity<>("User " + userId + " logged out", HttpStatus.OK);
    //    }

    @PostMapping(value = "/register")
    public ResponseEntity<Long> registerUser(@RequestBody @NonNull User user) {
        return ResponseEntity.status(201).body(userRepository.saveAndFlush(user).getId());
    }

}
