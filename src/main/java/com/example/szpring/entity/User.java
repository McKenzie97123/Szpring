package com.example.szpring.entity;

import java.sql.Timestamp;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String surName;
    private String hobby;
    private Timestamp createDate;
    private Integer age;

    public User() {

    }
}