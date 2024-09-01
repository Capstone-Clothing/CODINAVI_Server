package com.example.CODINAVI.dto.response.cloth;

public class ClothHistoryResponse {

    private String color;
    private String pattern;
    private String type;

    public ClothHistoryResponse(String color, String pattern, String type) {
        this.color = color;
        this.pattern = pattern;
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public String getPattern() {
        return pattern;
    }

    public String getType() {
        return type;
    }
}
