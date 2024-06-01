package com.example.CODINAVI.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeatherInfoResponse {

    private List<InfoFromDateResponse> infoFromDateList;

    public WeatherInfoResponse(List<InfoFromDateResponse> infoFromDateList) {
        this.infoFromDateList = infoFromDateList;
    }
}
