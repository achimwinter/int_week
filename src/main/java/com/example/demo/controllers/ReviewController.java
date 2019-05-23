package com.example.demo.controllers;

import lombok.Data;

import com.example.demo.services.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity postReview(@RequestParam Map<String, String> body) throws Exception {
        this.reviewService.saveReview(Long.parseLong(body.get("productId")),
            Long.parseLong(body.get("stars")),
            Long.parseLong(body.get("userId")),
            body.get("note"));

        return ResponseEntity.status(201).build();
    }

}

@Data
class ReviewRequest {
    private Long productId;
    private Long stars;
    private String note;
    private Long userId;
}
