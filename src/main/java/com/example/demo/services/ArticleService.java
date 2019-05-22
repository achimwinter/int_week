package com.example.demo.services;

import com.example.demo.models.Article;
import com.example.demo.models.User;
import com.example.demo.repositories.ArticleRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> list() {
        return articleRepository.findAll();
    }
}
