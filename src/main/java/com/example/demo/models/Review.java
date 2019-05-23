package com.example.demo.models;

import lombok.*;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "product")
public class Review {

    @Id
    @GeneratedValue
    private Long id;
    // TODO Entity Dinge tun

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author", nullable = false)
    private User author;

    private String content;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date creationDate;

    private Long stars; // from 1-5 stars

    public String getCreationDateAsString(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(creationDate);
        return strDate;
    }
}
