package com.example.CODINAVI.domain.cloth;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendHistoryRepository extends JpaRepository<RecommendHistory, Long> {

    RecommendHistory findByParentId(Long parentId);
}
