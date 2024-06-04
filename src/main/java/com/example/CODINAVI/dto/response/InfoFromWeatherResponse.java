package com.example.CODINAVI.dto.response;

import lombok.Getter;

@Getter
public class InfoFromWeatherResponse {

    private String date;
    private InfoFromTimeResponse info;

    public InfoFromWeatherResponse(String date, InfoFromTimeResponse info) {
        this.date = date;
        this.info = info;
    }
}
