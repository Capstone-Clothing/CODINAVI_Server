package com.example.CODINAVI.domain.color;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ColorRecommendHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long parentId;
    private String result;

    public ColorRecommendHistory() {

    }

    public ColorRecommendHistory(Long parentId, String result) {
        this.parentId = parentId;
        this.result = result;
    }


    public Long getParentId() {
        return parentId;
    }

    public String getResult() {
        return result;
    }
}
