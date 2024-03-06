package com.example.CODINAVI.controller;

import com.example.CODINAVI.entity.Codi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CodiController {

    @GetMapping("/clothInfo")
    @ResponseBody
    public Codi codiApi(@RequestParam("name") String name) {
        Codi codi = new Codi();
        codi.setName(name);
        return codi;
    }

}
