package com.example.CODINAVI.controller;

import com.example.CODINAVI.entity.Codi;
import com.example.CODINAVI.entity.Weather;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    @GetMapping("/clothInfo")
    @ResponseBody
    public Weather weatherApi(@RequestParam("temp") Double temp) {
        Weather weather = new Weather();
        weather.setTemp(temp);
        weather.getClothInfo();
        return weather;
    }
}
