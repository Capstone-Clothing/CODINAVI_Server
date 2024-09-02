package com.example.CODINAVI.domain.cloth;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ClothHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String color;
    private String pattern;
    private String type;

    public ClothHistory() {
    }

    public ClothHistory(String color, String pattern, String type) {
        this.color = color;
        this.pattern = pattern;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public String getPattern() {
        return pattern;
    }

    public String getType() {
        return type;
    }
}
