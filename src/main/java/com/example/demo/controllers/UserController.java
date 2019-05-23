package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    //    @PostMapping(value = "/login")
    //    public ResponseEntity<String> login(@PathVariable long userId) {
    //        return new ResponseEntity<>("User " + userId + " logged in successfully", HttpStatus.OK);
    //    }
    //
    //    @PostMapping(value = "/logout")
    //    public ResponseEntity<String> logout(@PathVariable long userId) {
    //        return new ResponseEntity<>("User " + userId + " logged out", HttpStatus.OK);
    //    }

    @GetMapping(value = "/registration")
    public String getRegistrationView() {
        return "registration";
    }

    @PostMapping(value = "/registration")
    public RedirectView createUser(@RequestParam Map<String, String> body) {
        User user = User.builder()
            .password(new BCryptPasswordEncoder().encode(body.get("password")))
            .username(body.get("username"))
            .email(body.get("email"))
            .build();

        this.userService.saveUser(user);
        return new RedirectView("/login");
    }


}
