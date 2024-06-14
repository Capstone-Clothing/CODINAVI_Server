package com.example.CODINAVI.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TempCodiRepository extends JpaRepository<Temp, Long> {
    Temp findByGenderAndMinTemp(String gender, Double minTemp);
}
