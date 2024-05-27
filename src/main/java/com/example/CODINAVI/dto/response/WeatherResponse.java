package com.example.CODINAVI.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponse {

    private String recInfo;
    private String weather;
    private String temp;

    public WeatherResponse() {
    }
}
