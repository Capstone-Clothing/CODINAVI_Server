package com.example.CODINAVI.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherInfoResponse {

    private String weather;
    private String temp;

    public WeatherInfoResponse() {
    }
}
