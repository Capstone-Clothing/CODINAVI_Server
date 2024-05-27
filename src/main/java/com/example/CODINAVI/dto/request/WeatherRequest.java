package com.example.CODINAVI.dto.request;

import lombok.Getter;

@Getter
public class WeatherRequest {

    private Double lat;
    private Double lon;
    private String time;

    public WeatherRequest(Double lat, Double lon, String time) {
        this.lat = lat;
        this.lon = lon;
        this.time = time;
    }

}

