package com.example.finraproject.model;

import lombok.Data;

import javax.persistence.*;

@Data

@Entity
@Table(name = "phones")
public class Phones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phoneNumber;

    public Phones() {
    }

}
