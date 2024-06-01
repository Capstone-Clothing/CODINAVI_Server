package com.example.CODINAVI.service.weather;

import com.example.CODINAVI.dto.request.TempRequest;
import com.example.CODINAVI.dto.request.WeatherRequest;
import com.example.CODINAVI.dto.response.*;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class WeatherService {
    private final static String BASE_URL = "http://apis.data.go.kr";

    private final LocalDateTime nowTime = LocalDateTime.now();
    String formatedNow = nowTime.format(DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss"));
    String subStringNowDay = formatedNow.substring(0, 8);
    String subStringNowTime = formatedNow.substring(9, 11);

    public WeatherInfoResponse getWeatherInfo(WeatherRequest request) {

        String base_date = subStringNowDay;
        String base_time = timeChange(subStringNowTime);

        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(BASE_URL);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

        WebClient webClient =
                WebClient.builder()
                        .uriBuilderFactory(factory)
                        .baseUrl(BASE_URL)
                        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .build();

        String response =
                webClient.get()
                        .uri(uriBuilder ->
                                uriBuilder.path("/1360000/VilageFcstInfoService_2.0/getVilageFcst")
                                        .queryParam("serviceKey", "cQvLnSjhJGqaRDQw3oWGS3PLYZ%2F0mK2hywjRA07%2F1Gc455UdpgXjjyTwTQJxQcI52xi6nl%2By9XgDlhQEF5o9Uw%3D%3D")
                                        .queryParam("pageNo", 1)
                                        .queryParam("numOfRows", 500)
                                        .queryParam("dataType", "JSON")
                                        .queryParam("base_date", base_date)
                                        .queryParam("base_time", base_time)
                                        .queryParam("nx", changeLatAndLonToCoordinate(request.getLat(), request.getLon()).getX())
                                        .queryParam("ny", changeLatAndLonToCoordinate(request.getLat(), request.getLon()).getY())
                                        .build())
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();

        JSONObject jsonObject = new JSONObject(response);
        JSONObject response1 = jsonObject.getJSONObject("response").getJSONObject("body");
        JSONObject items = response1.getJSONObject("items");
        JSONArray itemList = items.getJSONArray("item");

        List<String> tempList = new ArrayList<>();
        List<String> weatherList = new ArrayList<>();
        List<String> timeList = new ArrayList<>();
        List<String> dayList = new ArrayList<>();

        for (int i = 0; i < itemList.length(); i++) {

            log.info("itemList = {}", itemList.getJSONObject(i));

            JSONObject item = itemList.getJSONObject(i);
            String fcstValue = item.getString("fcstValue");
            String category = item.getString("category");
            String fcstTime = item.getString("fcstTime");
            String fcstDate = item.getString("fcstDate");

            if (category.equals("SKY")) {
                if (fcstValue.equals("1")) {
                    weatherList.add("맑음");
                } else if (fcstValue.equals("2")) {
                    weatherList.add("비");
                } else if (fcstValue.equals("3")) {
                    weatherList.add("구름많음");
                } else if (fcstValue.equals("4")) {
                    weatherList.add("흐림");
                }
            }

            if (category.equals("TMP")) {
                dayList.add(fcstDate);
                timeList.add(fcstTime);
                tempList.add(fcstValue);
            }

        }

        List<InfoFromDateResponse> everyInfoResponse = new ArrayList<>();
        List<InfoFromTimeResponse> everyTimeResponse = new ArrayList<>();
        List<InfoFromDateResponse> test = new ArrayList<>();

        for (int i = 0; i < weatherList.size(); i++) {

//            everyInfoResponse.add(new InfoFromDateResponse(dayList.get(i), new InfoFromTimeResponse(timeList.get(i), weatherList.get(i), tempList.get(i))));
            everyInfoResponse.add(new InfoFromDateResponse(dayList.get(i), new InfoFromTimeResponse(timeList.get(i), weatherList.get(i), tempList.get(i))));
        }
//        everyInfoResponse.add();

        WeatherInfoResponse weatherInfoResponse = new WeatherInfoResponse(everyInfoResponse);

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

    public LatAndLonTransferResponse changeLatAndLonToCoordinate(Double lat, Double lon) {

        LatAndLonTransferResponse response = new LatAndLonTransferResponse(0,0);

        double RE = 6371.00877;
        double GRID = 5.0; // 격자 간격(km)
        double SLAT1 = 30.0; // 투영 위도1(degree)
        double SLAT2 = 60.0; // 투영 위도2(degree)
        double OLON = 126.0; // 기준점 경도(degree)
        double OLAT = 38.0; // 기준점 위도(degree)
        double XO = 43; // 기준점 X좌표(GRID)
        double YO = 136; // 기1준점 Y좌표(GRID)

        double DEGRAD = Math.PI / 180.0;
        double RADDEG = 180.0 / Math.PI;

        double re = RE / GRID;
        double slat1 = SLAT1 * DEGRAD;
        double slat2 = SLAT2 * DEGRAD;
        double olon = OLON * DEGRAD;
        double olat = OLAT * DEGRAD;

        double sn = Math.tan(Math.PI * 0.25 + slat2 * 0.5) / Math.tan(Math.PI * 0.25 + slat1 * 0.5);
        sn = Math.log(Math.cos(slat1) / Math.cos(slat2)) / Math.log(sn);
        double sf = Math.tan(Math.PI * 0.25 + slat1 * 0.5);
        sf = Math.pow(sf, sn) * Math.cos(slat1) / sn;
        double ro = Math.tan(Math.PI * 0.25 + olat * 0.5);
        ro = re * sf / Math.pow(ro, sn);

        double ra = Math.tan(Math.PI * 0.25 + lat * DEGRAD * 0.5);
        ra = re * sf / Math.pow(ra, sn);
        double theta = lon * DEGRAD - olon;
        if (theta > Math.PI) theta -= 2.0 * Math.PI;
        if (theta < -Math.PI) theta += 2.0 * Math.PI;

        theta *= sn;
        double x = Math.floor(ra*Math.sin(theta) + XO + 0.5);
        double y = Math.floor(ro - ra * Math.cos(theta) + YO + 0.5);

        response.setX((int) x);
        response.setY((int) y);

        return response;
    }

    public String timeChange(String time) {
        // 현재 시간에 따라 데이터 시간 설정(3시간 마다 업데이트) //
        switch(time) {

            case "02":
            case "03":
            case "04":
                time = "0200";
                break;
            case "05":
            case "06":
            case "07":
                time = "0500";
                break;
            case "08":
            case "09":
            case "10":
                time = "0800";
                break;
            case "11":
            case "12":
            case "13":
                time = "1100";
                break;
            case "14":
            case "15":
            case "16":
                time = "1400";
                break;
            case "17":
            case "18":
            case "19":
                time = "1700";
                break;
            case "20":
            case "21":
            case "22":
                time = "2000";
                break;
            case "23":
            case "00":
            case "01":
                time = "2300";

        }
        return time;
    }

}
