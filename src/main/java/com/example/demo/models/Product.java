package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    private List<Review> reviews;

    public int getScore(){
        // TODO Funktion als Durchschnittsscore

        return 4;
    }


        return 4;
    }
    // standard constructors

    // standard getters and setters
}