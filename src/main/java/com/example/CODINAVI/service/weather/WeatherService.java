package com.example.CODINAVI.service.weather;

import com.example.CODINAVI.dto.request.TempRequest;
import com.example.CODINAVI.dto.request.WeatherRequest;
import com.example.CODINAVI.dto.response.CodiForWeatherResponse;
import com.example.CODINAVI.dto.response.WeatherInfoResponse;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class WeatherService {

    private LocalDateTime nowTime = LocalDateTime.now();
    String formatedNow = nowTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    String subStringNowDay = formatedNow.substring(0,10);
    String subStringNowTime = formatedNow.substring(11,13);

    public WeatherInfoResponse getWeatherInfo(WeatherRequest request) {

        WebClient webClient =
                WebClient.builder()
                        .baseUrl("https://api.openweathermap.org")
                        .build();

        Map<String, Object> response =
                webClient.get()
                         .uri(uriBuilder ->
                                uriBuilder.path("/data/2.5/forecast")
                                        .queryParam("lat",request.getLat())
                                        .queryParam("lon", request.getLon())
                                        .queryParam("appid","2d360c1fe9d2bade8fc08a1679683e24")
                                        .build())
                         .retrieve()
                         .bodyToMono(Map.class)
                         .block();

        HashMap<String, String> weather = new HashMap<>();

        JSONObject jsonObject = new JSONObject(response);
        JSONArray weatherList = jsonObject.getJSONArray("list");

        for (int i = 0; i < weatherList.length(); i++) {
            String jsonTime = jsonObject.getJSONArray("list").getJSONObject(i).getString("dt_txt");
            String subStringJsonDay = jsonTime.substring(0, 10);
            String subStringJsonTime = jsonTime.substring(11, 13);

            if ((subStringJsonDay.equals(subStringNowDay)) && (Integer.parseInt(subStringJsonTime) >= Integer.parseInt(subStringNowTime))) {
                weather.put(subStringJsonTime + "시", jsonObject.getJSONArray("list").getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("main"));
            }
        }

        log.info("checkWeatherList = {}", weather);

        Double kelvin = jsonObject.getJSONArray("list").getJSONObject(0).getJSONObject("main").getDouble("temp");
        String celsius = changeKelvinToCelsius(kelvin);

        WeatherInfoResponse weatherInfoResponse = new WeatherInfoResponse();

        weatherInfoResponse.setWeather(weather.get(request.getTime()));
        log.info("getWeatherInfo = {}", request.getTime());
        log.info("getWeatherInfo = {}", weatherInfoResponse.getWeather());
        weatherInfoResponse.setTemp(celsius);

        return weatherInfoResponse;
    }
    public CodiForWeatherResponse getRecInfo(TempRequest request) {

        String recInfo;

        if (request.getTemp() >= 28) {
            recInfo = "상의는 민소매, 반팔을 추천해드리며 하의는 짧은 치마, 린넨 옷, 반바지를 추천드립니다.";
        } else if (request.getTemp() >= 23 && request.getTemp() <= 27.9) {
            recInfo = "상의는 얇은 셔츠, 반팔을 추천해드리며 하의는 반바지, 면바지를 추천드립니다.";
        } else if (request.getTemp() >= 20 && request.getTemp() <= 22.9) {
            recInfo = "상의는 블라우스, 긴팔티를 추천해드리며 하의는 면바지, 슬랙스를 추천드립니다.";
        } else if (request.getTemp() >= 17 && request.getTemp() <= 19.9) {
            recInfo = "상의는 얇은 가디건, 니트, 맨투맨, 후드를 추천해드리며 하의는 긴 바지를 추천드립니다.";
        } else if (request.getTemp() >= 12 && request.getTemp() <= 16.9) {
            recInfo = "상의는 자켓 또는 청자켓, 가디건, 니트를 추천해드리며 하의는 스타킹, 청바지를 추천드립니다.";
        } else if (request.getTemp() >= 9 && request.getTemp() <= 11.9) {
            recInfo = "상의는 트렌치 코트, 야상, 점퍼를 추천해드리며 하의는 스타킹, 기모바지를 추천드립니다.";
        } else if (request.getTemp() >= 5 && request.getTemp() <= 8.9) {
            recInfo = "상의는 울 코트 또는 가죽 옷과 히트텍을 추천해드리며 하의 또한 기모가 들어간 바지를 추천드립니다.";
        } else {
            recInfo = "상의는 패딩, 두꺼운 코트, 누빔 옷, 기모가 들어간 소재, 그리고 목도리를 걸치시는 것을 추천드립니다.";
        }

        CodiForWeatherResponse response = new CodiForWeatherResponse(recInfo);

        return response;
    }

    public String changeKelvinToCelsius(Double temp) {
        Double changedTemp = (temp - 273.15);
        DecimalFormat df = new DecimalFormat("#.#");
        df.setRoundingMode(RoundingMode.DOWN);
        return df.format(changedTemp);
    }

}
