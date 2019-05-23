package com.example.demo.controllers;

import lombok.Data;

import com.example.demo.services.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity postReview(@RequestBody ReviewRequest reviewRequest) throws Exception {
        this.reviewService.saveReview(reviewRequest.getProductId(),
            reviewRequest.getStars(),
            reviewRequest.getUserId(),
            reviewRequest.getNote());

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
