package com.example.CODINAVI.dto.request;

import lombok.Getter;

@Getter
public class WeatherRequest {

    private final Double temp;
    private Double lat;
    private Double lon;
    private String time;

    public WeatherRequest(Double temp, Double lat, Double lon, String time) {
        this.temp = temp;
        this.lat = lat;
        this.lon = lon;
        this.time = time;
    }

}

