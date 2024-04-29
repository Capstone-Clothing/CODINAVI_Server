package com.example.CODINAVI.domain.entity;

import jakarta.persistence.*;

@Entity
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String color;

    @Column(nullable = false, length = 30)
    private String matchColor;

    public Color() {

    }

    public Color(String color, String matchColor) {
        this.color = color;
        this.matchColor = matchColor;
    }

    public Long getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public String getMatchColor() {
        return matchColor;
    }

}
