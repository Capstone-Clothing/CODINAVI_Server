package com.example.CODINAVI.controller.color;

import com.example.CODINAVI.dto.request.color.ColorRecRequest;
import com.example.CODINAVI.dto.response.color.ColorRecResponse;
import com.example.CODINAVI.service.color.ColorRecService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ColorController {

    private ColorRecService colorRecService;

    public ColorController(ColorRecService colorRecService) {
        this.colorRecService = colorRecService;
    }

    @GetMapping("/color/recommend")
    public ColorRecResponse recommend(ColorRecRequest request) {
        return colorRecService.getColorRec(request);
    }
}
//test

//ekekekek
