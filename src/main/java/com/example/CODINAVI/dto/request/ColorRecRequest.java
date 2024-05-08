package com.example.CODINAVI.dto.request;

public class ColorRecRequest {

    private String color;
    private String rgb;
    private String pantoneRgb;
    private String hsv;
    private String tcx;
    private String colorForApp;
    private String matchColor;

    public ColorRecRequest(String color) {
        this.color = color;
    }

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
