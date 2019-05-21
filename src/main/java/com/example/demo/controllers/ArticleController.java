package com.example.demo.controllers;

import com.example.demo.models.Article;
import com.example.demo.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Article> getAllArticles(@RequestParam @Nullable String category) {
        // TODO Pagination
        System.out.println("test");
        return articleRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Article createArticle(@RequestBody Article article) {
        return articleRepository.save(article);
    }

}
