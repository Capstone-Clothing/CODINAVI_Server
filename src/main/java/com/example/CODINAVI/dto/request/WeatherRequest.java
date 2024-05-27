package com.example.CODINAVI.dto.request;

import lombok.Getter;

@Getter
public class WeatherRequest {

    private final Double lat;
    private final Double lon;
    private final String time;

    public WeatherRequest(Double lat, Double lon, String time) {
        this.lat = lat;
        this.lon = lon;
        this.time = time;
    }

}

