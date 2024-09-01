package com.example.CODINAVI.dto.request.weather;

import lombok.Getter;

@Getter
public class WeatherRequest {

    private final Double lat;
    private final Double lon;

    public WeatherRequest(Double lat, Double lon) {
        this.lat = lat;
        this.lon = lon;
    }

}

