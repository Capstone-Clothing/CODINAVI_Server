package com.example.CODINAVI.controller.color;

import com.example.CODINAVI.dto.request.ColorCreateRequest;
import com.example.CODINAVI.dto.request.ColorRecRequest;
import com.example.CODINAVI.dto.response.ColorRecResponse;
import com.example.CODINAVI.service.color.ColorRecService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ColorRecController {

    private ColorRecService colorRecService;

    public ColorRecController(ColorRecService colorRecService) {
        this.colorRecService = colorRecService;
    }

    @GetMapping("/color/recommend")
    public ColorRecResponse recommend(ColorRecRequest request) {
        return colorRecService.getColorRec(request);
    }

    @PostMapping("/color/insert")
    public void insert(@RequestBody ColorCreateRequest request) {
        colorRecService.insertColor(request);
    }
    //connection test
}