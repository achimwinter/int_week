package com.example.demo.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String fullname;
    private String title;
    private String companyName;
    private String companyAddress;
    private String city;
    private String job;
    private String phoneNumber;
    private String email;
    private String country;
    private String zip;
    private Boolean superuser;
    private Boolean delsoft; // dsgvo???
    private String note;

    // standard constructors

    // standard getters and setters
}