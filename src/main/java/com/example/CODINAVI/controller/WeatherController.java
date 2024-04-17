package com.example.CODINAVI.controller;

import com.example.CODINAVI.dto.WeatherDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    @GetMapping("/clothInfo")
    @ResponseBody
    public WeatherDto weatherApi(@RequestParam("temp") Double temp) {
        WeatherDto weatherDto = new WeatherDto();
        weatherDto.setTemp(temp);
        weatherDto.getClothInfo();
        return weatherDto;
    }
}

