package com.example.CODINAVI.dto.response.cloth;

public class ClothHistoryResponse {

    private Long id;
    private String color;
    private String pattern;
    private String type;

    public ClothHistoryResponse(Long id, String color, String pattern, String type) {
        this.id = id;
        this.color = color;
        this.pattern = pattern;
        this.type = type;
    }

    public Long getId() {
        return id;
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
