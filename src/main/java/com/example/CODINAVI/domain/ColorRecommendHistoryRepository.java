package com.example.CODINAVI.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRecommendHistoryRepository extends JpaRepository<ColorRecommendHistory, Integer> {
    ColorRecommendHistory findByParentId(Long id);
}
