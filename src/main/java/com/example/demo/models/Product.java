package com.example.demo.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.List;


@Entity
@Data
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    private String productName;
    private Category category;
    private String imagepath; // TODO in ressources einfach images ablegen
    private BigDecimal price;
    private Long stock;
    private String note;
    private List<Review> reviews;
    // TODO Funktion als Durchschnittsscore

    // standard constructors

    // standard getters and setters
}