package com.example.CODINAVI.dto.response;

import lombok.Getter;

@Getter
public class CodiForWeatherResponse {

    private String recInfo;

    public CodiForWeatherResponse(String recInfo) {
        this.recInfo = recInfo;
    }
}
