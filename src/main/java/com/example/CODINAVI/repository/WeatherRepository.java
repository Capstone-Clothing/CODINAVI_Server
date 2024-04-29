package com.example.CODINAVI.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WeatherRepository {

    private JdbcTemplate jdbcTemplate;

    public WeatherRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



}
