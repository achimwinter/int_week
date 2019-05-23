package com.example.demo.services;

import javassist.NotFoundException;

import com.example.demo.models.Product;
import com.example.demo.models.Review;
import com.example.demo.models.User;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.ReviewRepository;
import com.example.demo.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

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

    public void saveReview(Long productId, Long stars, Long userId, String note) throws Exception{
        Product productToUpdate = this.productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product not found"));
        User reviewUser = this.userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));

        Review review = new Review();
        review.setAuthor(reviewUser);
        review.setContent(note);
        review.setCreationDate(new Date());
        review.setStars(stars);
        review.setProduct(productToUpdate);

        this.reviewRepository.save(review);
    }
}
