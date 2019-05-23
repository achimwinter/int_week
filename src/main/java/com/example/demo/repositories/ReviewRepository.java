package com.example.demo.repositories;

import com.example.demo.models.Product;
import com.example.demo.models.Review;
import com.example.demo.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> getReviewsByAuthorAndProduct(User author, Product product);

    List<Review> getReviewsByProduct(Product product);

    List<Review> getReviewsByAuthor(User author);

}