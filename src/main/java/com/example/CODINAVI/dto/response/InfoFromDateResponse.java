package com.example.CODINAVI.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class InfoFromDateResponse {

    private String date;
    private List<InfoFromTimeResponse> info;

    public InfoFromDateResponse(String date, List<InfoFromTimeResponse> info) {
        this.date = date;
        this.info = info;
    }
}
