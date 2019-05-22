package com.example.demo.models;

import lombok.*;

import javax.persistence.*;

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
}
