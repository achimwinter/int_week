package com.example.demo.controllers;

import com.example.demo.services.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

import java.util.Map;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public RedirectView postReview(@RequestParam Map<String, String> body, HttpServletRequest request) throws Exception {
        this.reviewService.saveReview(Long.parseLong(body.get("productId")),
            Long.parseLong(body.get("stars")),
            Long.parseLong(body.get("userId")),
            body.get("note"));

        String referer = request.getHeader("Referer");
        if (referer.isEmpty())
            return new RedirectView("/");
        return new RedirectView(referer);
    }

}
