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

    private Long userId;
    private String color;
    private String pattern;
    private String type;

    public ClothHistory() {
    }

    public ClothHistory(Long userId, String color, String pattern, String type) {
        this.userId = userId;
        this.color = color;
        this.pattern = pattern;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
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
