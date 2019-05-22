package com.example.demo.webcontrollers;

import com.example.demo.models.Article;
import com.example.demo.repositories.ArticleRepository;
import com.example.demo.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class WelcomeController {

    @Autowired
    ArticleRepository articleRepository;

    private List<String> tasks = Arrays.asList("a", "b", "c", "d", "e", "f", "g");

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("tasks", tasks);
        model.addAttribute("products", articleRepository.findAll());

        ArticleService articleService = new ArticleService();
        List<Article> allProducts = articleService.list();

        model.addAttribute("prods", allProducts);

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