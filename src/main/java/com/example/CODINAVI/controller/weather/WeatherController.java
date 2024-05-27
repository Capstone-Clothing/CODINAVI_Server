package com.example.CODINAVI.controller.weather;

import com.example.CODINAVI.dto.request.TempRequest;
import com.example.CODINAVI.dto.request.WeatherRequest;
import com.example.CODINAVI.dto.response.WeatherResponse;
import com.example.CODINAVI.service.weather.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    private WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public WeatherResponse getWeather(WeatherRequest request) {
        return weatherService.getWeatherInfo(request);
    }
    @GetMapping("/weather/clothInfo")
    public WeatherResponse weatherApi(TempRequest request) {
        return weatherService.getRecInfo(request);
    }
}

