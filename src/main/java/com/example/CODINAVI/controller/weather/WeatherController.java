package com.example.CODINAVI.controller.weather;

import com.example.CODINAVI.dto.request.WeatherCodiRequest;
import com.example.CODINAVI.dto.request.WeatherRequest;
import com.example.CODINAVI.dto.response.weather.WeatherCodiResponse;
import com.example.CODINAVI.dto.response.weather.WeatherInfoResponse;
import com.example.CODINAVI.service.weather.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public WeatherInfoResponse getWeatherInfo(WeatherRequest request) {
        return weatherService.getWeatherInfo(request);
    }

    @GetMapping("/weather/clothInfo")
    public WeatherCodiResponse weatherApi(WeatherCodiRequest request) {
        return weatherService.getRecInfo(request);
    }
}

