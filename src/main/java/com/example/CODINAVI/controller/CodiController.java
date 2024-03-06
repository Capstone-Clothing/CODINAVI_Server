package com.example.CODINAVI.controller;

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

    static class Codi {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name + "hihi";
        }
    }
}
