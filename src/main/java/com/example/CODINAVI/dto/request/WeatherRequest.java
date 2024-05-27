package com.example.CODINAVI.dto.request;

import lombok.Getter;

@Getter
public class WeatherRequest {

    private final Double temp;
    private Double lat;
    private Double lon;

    public WeatherRequest(Double temp, Double lat, Double lon) {
        this.temp = temp;
        this.lat = lat;
        this.lon = lon;
    }

}

