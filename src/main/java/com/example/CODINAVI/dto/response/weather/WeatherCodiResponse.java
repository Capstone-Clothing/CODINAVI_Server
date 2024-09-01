package com.example.CODINAVI.dto.response.weather;

import lombok.Getter;

@Getter
public class WeatherCodiResponse {

    private String codi;
    private String clothRec;

    public WeatherCodiResponse(String codi, String clothRec) {
        this.codi = codi;
        this.clothRec = clothRec;
    }

}
