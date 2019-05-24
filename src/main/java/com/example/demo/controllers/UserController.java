package com.example.demo.controllers;

import lombok.SneakyThrows;

import com.example.demo.models.User;
import com.example.demo.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("activeuser", user);

        return "profile";
    }

    @GetMapping(value = "/registration")
    public String getRegistrationView() {
        return "registration";
    }

    @PostMapping(value = "/registration")
    public RedirectView createUser(@RequestParam Map<String, String> body) {
        User user = checkAndCreateUser(body);

        this.userService.saveUser(user);
        return new RedirectView("/login");
    }

    @SneakyThrows
    private User checkAndCreateUser(Map<String, String> body) {
        User user = User.builder()
            .password(new BCryptPasswordEncoder().encode(body.get("password")))
            .username(body.get("username"))
            .email(body.get("email"))
            .build();

        if (userService.checkIfUserExists(user.getUsername()))
            throw new Exception("User already exists");

        if (user.getPassword().isEmpty() || user.getUsername().isEmpty() || user.getEmail().isEmpty()) {
            throw new Exception("User was not valid");
        }
        return user;
    }

}
