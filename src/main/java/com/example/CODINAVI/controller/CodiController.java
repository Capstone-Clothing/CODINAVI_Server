package com.example.CODINAVI.controller;

import org.springframework.web.bind.annotation.*;
import java.io.ByteArrayInputStream;
import java.util.Base64;
import java.util.Map;

@RestController
@RequestMapping("/codi")
public class CodiController {

    @GetMapping("/clothInfo")
    @ResponseBody
    public String handleImageUpload(@RequestBody Map<String, String> request) {
        String imageData = request.get("image");

        try {
            byte[] decodedImage = Base64.getDecoder().decode(imageData);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(decodedImage);
            return "data:image/jpeg;base64," + imageData;
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}";
        }
    }
    // test

}
