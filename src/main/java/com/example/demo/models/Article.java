package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;


@Entity
public class Article {

    @Id
    @GeneratedValue
    private Long id;
    private String productNumber;
    private String productName;
    private String categoryNo;
    private String category;
    private String imagepath;
    private Boolean isNewProduct;
    private BigDecimal price1;
    private BigDecimal price2;
    private String realstock;
    private String stock;
    private String cas;
    private String mdlint;
    private String formula;
    private String weight;
    private String delsoft;
    private String note;


    // standard constructors

    // standard getters and setters
}