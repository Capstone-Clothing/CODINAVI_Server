package com.example.CODINAVI.dto.request.cloth;

public class ClothHistoryRequest {

    private Long userId;
    private String color;
    private String pattern;
    private String type;

    public Long getUserId() {
        return userId;
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
