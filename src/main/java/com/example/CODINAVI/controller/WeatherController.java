package com.example.CODINAVI.controller;

import com.example.CODINAVI.dto.request.WeatherRequest;
import com.example.CODINAVI.dto.response.WeatherResponse;
import com.example.CODINAVI.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    private WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather/clothInfo")
    public WeatherResponse weatherApi(WeatherRequest request) {

        return weatherService.getRecInfo(request);

    }
}

