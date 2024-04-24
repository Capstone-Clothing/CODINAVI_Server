package com.example.CODINAVI.dto.request;

public class WeatherRequest {
    private Double temp;

    public WeatherRequest(Double temp) {
        this.temp = temp;
    }

    public Double getTemp() {
        return temp;
    }
}

