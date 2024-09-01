package com.example.CODINAVI.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RecommendHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String result;

    public RecommendHistory(String result) {
        this.result = result;
    }

    public RecommendHistory() {
    }

    public String getResult() {
        return result;
    }
}
