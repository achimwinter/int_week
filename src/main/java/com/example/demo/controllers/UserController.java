package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/login/{userId}", method = RequestMethod.POST)
    public ResponseEntity<String> login(@PathVariable long userId) {
        return new ResponseEntity<>("User " + userId + " logged in successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/logout/{userId}", method = RequestMethod.POST)
    public ResponseEntity<String> logout(@PathVariable long userId) {
        return new ResponseEntity<>("User " + userId + " logged out", HttpStatus.OK);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public User registerUser(@RequestBody @NonNull User user) {
        return userRepository.save(user);
    }

}
