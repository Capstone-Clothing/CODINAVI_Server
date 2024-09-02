package com.example.CODINAVI.domain.cloth;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RecommendHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long parentId;
    private String result;

    public RecommendHistory(Long parentId, String result) {
        this.parentId = parentId;
        this.result = result;
    }

    public RecommendHistory() {

    }

    public Long getParentId() {
        return parentId;
    }

    public String getResult() {
        return result;
    }
}
