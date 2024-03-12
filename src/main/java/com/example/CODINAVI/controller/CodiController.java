package com.example.CODINAVI.controller;

import com.example.CODINAVI.entity.Codi;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.Base64;
import java.util.Map;

@RestController
@RequestMapping("/codi")
public class CodiController {

    @PostMapping("/clothInfo")
    public String handleImageUpload(@RequestBody Map<String, String> request) {
        String imageData = request.get("image");

        try {
            byte[] decodedImage = Base64.getDecoder().decode(imageData);
            return "이 이미지는 이렇습니다!!";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}";
        }
    }

}
