package com.example.demo.models;

import lombok.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
@Indexed
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

    public int getScore(){
        // TODO Funktion als Durchschnittsscore

        return 4;
    }

    @Formula("(select avg(o.creation_date) from Reviews r where r.product = id group by r.product)")
    private Double averageScore;


    // standard constructors

    // standard getters and setters
}