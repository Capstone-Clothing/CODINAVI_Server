package com.example.CODINAVI.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothHistoryRepository extends JpaRepository<ClothHistory, Long> {

}
