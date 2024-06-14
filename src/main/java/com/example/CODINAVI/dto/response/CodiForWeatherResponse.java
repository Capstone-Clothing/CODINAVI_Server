package com.example.CODINAVI.dto.response;

import com.example.CODINAVI.domain.Temp;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CodiForWeatherResponse {

    private String codi;
    private String clothRec;

    public CodiForWeatherResponse(String codi, String clothRec) {
        this.codi = codi;
        this.clothRec = clothRec;
    }

}
