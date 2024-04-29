package com.example.CODINAVI.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRecRepository extends JpaRepository<Color, Long> {

    Color findByColor(String color);
}
