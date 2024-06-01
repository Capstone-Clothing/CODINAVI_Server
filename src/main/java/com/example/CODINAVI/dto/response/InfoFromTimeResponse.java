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

    public InfoFromTimeResponse() {
    }

    public InfoFromTimeResponse(String time, String weather, String temp, String hum, String precipitation) {
        this.time = time;
        this.weather = weather;
        this.temp = temp;
        this.hum = hum;
        this.precipitation = precipitation;
    }
}
