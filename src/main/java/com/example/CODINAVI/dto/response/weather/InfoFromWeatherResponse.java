package com.example.CODINAVI.dto.response.weather;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class InfoFromWeatherResponse {

    private LocalDateTime nowTime = LocalDateTime.now();
    private String formatedNow = nowTime.format(DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss"));
    private String subStringNowDay = formatedNow.substring(0, 8);

    private String date;
    private String lowTemp;
    private String highTemp;
    private InfoFromTimeResponse info;

    public InfoFromWeatherResponse(String date, String lowTemp, String highTemp, InfoFromTimeResponse info) {
        this.date = date;
        if (date.equals(subStringNowDay)) {
            this.lowTemp = lowTemp;
            this.highTemp = highTemp;
        }
        this.info = info;
    }

    public String getDate() {
        return date;
    }

    public String getLowTemp() {
        return lowTemp;
    }

    public String getHighTemp() {
        return highTemp;
    }

    public InfoFromTimeResponse getInfo() {
        return info;
    }
}
