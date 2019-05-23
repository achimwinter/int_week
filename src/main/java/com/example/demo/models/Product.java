package com.example.demo.models;

import lombok.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
//@ToString(exclude = "reviews")
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    private String productName;

    @ManyToOne
    private Category category;
    private String imagepath; // TODO in ressources einfach images ablegen
    private BigDecimal price;
    private Long stock;
    private String note;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "product")
    private List<Review> reviews = new ArrayList<>();


    public Double getAverageScore(){
        return reviews.stream().mapToDouble(x -> (double)x.getStars()).average().orElse(5.d);
    }

    public Long getAverageScoreRound(){
        return Math.round(reviews.stream().mapToDouble(x -> (double)x.getStars()).average().orElse(5.d));
    }

    // standard constructors

    // standard getters and setters
}