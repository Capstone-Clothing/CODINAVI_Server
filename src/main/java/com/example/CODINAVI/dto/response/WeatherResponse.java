package com.example.CODINAVI.dto.response;

public class WeatherResponse {

    private String recInfo;

    public WeatherResponse(String recInfo) {
        this.recInfo = recInfo;
    }

    public String getRecInfo() {
        return recInfo;
    }
}
