package com.example.demo.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String email;
//    private String title;
//    private String companyName;
//    private String companyAddress;
//    private String city;
//    private String job;
//    private String phoneNumber;
//    private String email;
//    private String country;
//    private String zip;
//    private Boolean superuser;
//    private Boolean delsoft; // dsgvo???
//    private String note;

    // standard constructors

    // standard getters and setters
}