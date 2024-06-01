package com.example.CODINAVI.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LatAndLonTransferResponse {

    private int x;
    private int y;

    public LatAndLonTransferResponse(int x, int y) {
        this.x = x;
        this.y = y;
    }


}
