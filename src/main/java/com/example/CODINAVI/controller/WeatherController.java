package com.example.CODINAVI.controller;

import com.example.CODINAVI.dto.request.WeatherRequest;
import com.example.CODINAVI.dto.response.WeatherResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    @GetMapping("/weather/clothInfo")
    public WeatherResponse weatherApi(WeatherRequest request) {

        String recInfo;

        if (request.getTemp() >= 28) {
            recInfo = "상의는 민소매, 반팔을 추천해드리며 하의는 짧은 치마, 린넨 옷, 반바지를 추천드립니다.";
        } else if (request.getTemp() >= 23 && request.getTemp() <= 27) {
            recInfo = "상의는 얇은 셔츠, 반팔을 추천해드리며 하의는 반바지, 면바지를 추천드립니다.";
        } else if (request.getTemp() >= 20 && request.getTemp() <= 22) {
            recInfo = "상의는 블라우스, 긴팔티를 추천해드리며 하의는 면바지, 슬랙스를 추천드립니다.";
        } else if (request.getTemp() >= 17 && request.getTemp() <= 19) {
            recInfo = "상의는 얇은 가디건, 니트, 맨투맨, 후드를 추천해드리며 하의는 긴 바지를 추천드립니다.";
        } else if (request.getTemp() >= 12 && request.getTemp() <= 16) {
            recInfo = "상의는 자켓 또는 청자켓, 가디건, 니트를 추천해드리며 하의는 스타킹, 청바지를 추천드립니다.";
        } else if (request.getTemp() >= 9 && request.getTemp() <= 11) {
            recInfo = "상의는 트렌치 코트, 야상, 점퍼를 추천해드리며 하의는 스타킹, 기모바지를 추천드립니다.";
        } else if (request.getTemp() >= 5 && request.getTemp() <= 8) {
            recInfo = "상의는 울 코트 또는 가죽 옷과 히트텍을 추천해드리며 하의 또한 기모가 들어간 바지를 추천드립니다.";
        } else {
            recInfo = "상의는 패딩, 두꺼운 코트, 누빔 옷, 기모가 들어간 소재, 그리고 목도리를 걸치시는 것을 추천드립니다.";
        }

        WeatherResponse response = new WeatherResponse(recInfo);

        return response;
    }
}

