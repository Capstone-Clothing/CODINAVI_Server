package com.example.CODINAVI.dto.response;

import lombok.Getter;

@Getter
public class InfoFromTimeResponse {

    private String time;
    private String weather;
    private String temp;
    private String hum;
    private String precipitationType;
    private String precipitation;
    private String precipitationProbability;

    public InfoFromTimeResponse() {
    }

    public InfoFromTimeResponse(String time, String weather, String temp, String hum, String precipitationType, String precipitation, String precipitationProbability) {
        this.time = time;
        this.weather = weather;
        this.temp = temp;
        this.hum = hum;
        this.precipitationType = precipitationType;
        this.precipitation = precipitation;
        this.precipitationProbability = precipitationProbability;
    }
}
