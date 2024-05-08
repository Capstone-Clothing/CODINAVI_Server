package com.example.CODINAVI.dto.request;

import jakarta.persistence.Column;

public class ColorCreateRequest {

    private String color;
    private String rgb;
    private String pantoneRgb;
    private String hsv;
    private String tcx;
    private String colorForApp;
    private String matchColor;

    public String getColor() {
        return color;
    }

    public String getRgb() {
        return rgb;
    }

    public String getPantoneRgb() {
        return pantoneRgb;
    }

    public String getHsv() {
        return hsv;
    }

    public String getTcx() {
        return tcx;
    }

    public String getColorForApp() {
        return colorForApp;
    }

    public String getMatchColor() {
        return matchColor;
    }
}
