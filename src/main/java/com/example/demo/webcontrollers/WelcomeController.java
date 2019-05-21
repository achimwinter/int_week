package com.example.demo.webcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class WelcomeController {

    private List<String> tasks = Arrays.asList("a", "b", "c", "d", "e", "f", "g");

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("tasks", tasks);

        return "welcome"; //view
    }

    @GetMapping("/hello")
    public String mainWithParam(
            @RequestParam(name = "name", required = false, defaultValue = "")
                    String name, Model model) {

        model.addAttribute("message", name);

        return "welcome"; //view
    }

}