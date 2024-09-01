package com.example.CODINAVI.dto.request.weather;

import lombok.Getter;

@Getter
public class WeatherCodiRequest {

    private Double temp;
    private String gender;

    public WeatherCodiRequest(Double temp, String gender) {
        this.temp = temp;
        this.gender = gender;
    }
}
