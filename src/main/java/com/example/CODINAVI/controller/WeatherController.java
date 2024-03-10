package com.example.CODINAVI.controller;

import com.example.CODINAVI.entity.Codi;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/weather")
public class WeatherController {
    @GetMapping("/clothInfo2")
    @ResponseBody
    public Codi codiApi(@RequestParam("name") String name) {
        Codi codi = new Codi();
        codi.setName(name);
        return codi;
    }
}
