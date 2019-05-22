package com.example.demo.services;

import com.example.demo.models.OrderList;
import com.example.demo.models.Product;
import com.example.demo.models.Review;
import com.example.demo.models.User;
import com.example.demo.repositories.OrderListRepository;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.ReviewRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getAllReviews(User user) {
        return reviewRepository.getReviewsByAuthor(user);

    }
    public List<Review> getAllReviews(Product product) {
        return reviewRepository.getReviewsByProduct(product);
    }
    public Double getAverageScore(Product product) {
        return reviewRepository.getReviewsByProduct(product).stream().mapToDouble(x -> (double)x.getStars()).average()
                .orElse(5.d);
    }
}
