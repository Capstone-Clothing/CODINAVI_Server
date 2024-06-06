package com.example.CODINAVI.dto.response;

import lombok.Getter;

@Getter
public class InfoFromWeatherResponse {

    private String date;
    private String lowTemp;
    private String highTemp;
    private InfoFromTimeResponse info;

    public InfoFromWeatherResponse(String date, String lowTemp, String highTemp, InfoFromTimeResponse info) {
        this.date = date;
        this.lowTemp = lowTemp;
        this.highTemp = highTemp;
        this.info = info;
    }
}
