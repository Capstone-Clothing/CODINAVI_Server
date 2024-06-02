package com.example.CODINAVI.dto.response;

import lombok.Getter;

@Getter
public class InfoFromTimeResponse {

    private String time;
    private String weather;
    private String temp;
//    private String tempMin;
//    private String tempMax;
    private String hum;
    private String precipitation;
    private String precipitationProbability;


    public InfoFromTimeResponse(String time, String weather, String temp, String hum, String precipitation, String precipitationProbability) {
        this.time = time;
        this.weather = weather;
        this.temp = temp;
        this.hum = hum;
        this.precipitation = precipitation;
        this.precipitationProbability = precipitationProbability;
    }
}
