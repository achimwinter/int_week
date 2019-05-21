package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @RequestMapping(method = RequestMethod.GET)
    public List<Object> getAllArticles(@RequestParam String category) {
        // TODO Pagination
        return Collections.emptyList();
    }

}
