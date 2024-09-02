package com.example.CODINAVI.domain.color;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRecRepository extends JpaRepository<Color, Long> {
    Color findByColorForApp(String color);
}