package com.example.CODINAVI.controller.weather;

import com.example.CODINAVI.dto.request.WeatherCodiRequest;
import com.example.CODINAVI.dto.request.WeatherRequest;
import com.example.CODINAVI.dto.response.WeatherCodiResponse;
import com.example.CODINAVI.dto.response.WeatherInfoResponse;
import com.example.CODINAVI.service.weather.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }
//
//    @PostMapping("/weather")
//    public void makeTable(@RequestBody ColorCreateRequest request) {
//        weatherService.makeTable(request);
//    }

    @GetMapping("/weather")
    public WeatherInfoResponse getWeather(WeatherRequest request) {
        return weatherService.getWeatherInfo(request);
    }

    @GetMapping("/weather/clothInfo")
    public WeatherCodiResponse weatherApi(WeatherCodiRequest request) {
        return weatherService.getRecInfo(request);
    }
}

