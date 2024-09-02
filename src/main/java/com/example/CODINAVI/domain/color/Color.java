package com.example.CODINAVI.domain.color;

import jakarta.persistence.*;

@Entity
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String color;

    private String rgb;

    private String pantoneRgb;

    private String hsv;

    private String tcx;

    private String colorForApp;

    @Column(nullable = false)
    private String matchColor;

    public Color() {

    }

    public Color(String color, String rgb, String pantoneRgb, String hsv, String tcx, String colorForApp, String matchColor) {
        this.color = color;
        this.rgb = rgb;
        this.pantoneRgb = pantoneRgb;
        this.hsv = hsv;
        this.tcx = tcx;
        this.colorForApp = colorForApp;
        this.matchColor = matchColor;
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
