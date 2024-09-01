package com.example.CODINAVI.dto.response.weather;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeatherInfoResponse {

    private List<InfoFromWeatherResponse> infoFromDateList;

    public WeatherInfoResponse(List<InfoFromWeatherResponse> infoFromDateList) {
        this.infoFromDateList = infoFromDateList;
    }
}
