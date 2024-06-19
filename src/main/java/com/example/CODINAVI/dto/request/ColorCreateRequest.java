package com.example.CODINAVI.dto.request;

import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class ColorCreateRequest {

    private String gender;
    private Double minTemp;
    private Double maxTemp;
    private String codi;
    private String clothRec;


    public ColorCreateRequest(String gender, Double minTemp, Double maxTemp, String codi, String clothRec) {
        this.gender = gender;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.codi = codi;
        this.clothRec = clothRec;
    }
}
