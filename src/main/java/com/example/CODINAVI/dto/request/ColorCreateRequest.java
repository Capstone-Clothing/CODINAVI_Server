package com.example.CODINAVI.dto.request;

public class ColorCreateRequest {

    private String color;
    private String matchColor;

    public ColorCreateRequest(String color, String matchColor) {
        this.color = color;
        this.matchColor = matchColor;
    }

    public String getColor() {
        return color;
    }

    public String getMatchColor() {
        return matchColor;
    }
}
