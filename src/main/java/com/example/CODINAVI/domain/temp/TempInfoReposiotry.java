package com.example.CODINAVI.domain.temp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TempInfoReposiotry extends JpaRepository<TempInfo, Long> {
    TempInfo findByGenderAndMinTemp(String gender, Double minTemp);
}
