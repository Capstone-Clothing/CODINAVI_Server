package com.example.CODINAVI.dto;

public class WeatherDto {
    private Double temp;
    private String clothInfo;

    public void setTemp(Double temp) {
        this.temp = temp;
    }
    public Double getTemp() {
        return temp;
    }

    public void setClothInfo(String clothInfo) {
        this.clothInfo = clothInfo;
    }
    public String getClothInfo() {
        return clothInfo;
    }
}

