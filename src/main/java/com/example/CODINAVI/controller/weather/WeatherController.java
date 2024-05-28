package com.example.CODINAVI.controller.weather;

import com.example.CODINAVI.dto.request.TempRequest;
import com.example.CODINAVI.dto.request.WeatherRequest;
import com.example.CODINAVI.dto.response.CodiForWeatherResponse;
import com.example.CODINAVI.dto.response.WeatherInfoResponse;
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
    public WeatherInfoResponse getWeather(WeatherRequest request) {
        return weatherService.getWeatherInfo(request);
    }
    @GetMapping("/weather/clothInfo")
    public CodiForWeatherResponse weatherApi(TempRequest request) {
        return weatherService.getRecInfo(request);
    }
}

