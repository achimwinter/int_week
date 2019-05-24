package com.example.demo.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
@ToString(exclude = "reviews")
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    private String productName;

    @ManyToOne
    private Category category;
    private String imagepath;
    private BigDecimal price;
    private Long stock;
    private String note;

    @OneToMany(cascade = CascadeType.ALL,
        fetch = FetchType.LAZY,
        mappedBy = "product")
    private List<Review> reviews = new ArrayList<>();

    public Double getAverageScore() {
        return reviews.stream().mapToDouble(x -> (double) x.getStars()).average().orElse(5.d);
    }

    public Long getAverageScoreRound() {
        return Math.round(reviews.stream().mapToDouble(x -> (double) x.getStars()).average().orElse(5.d));
    }

}