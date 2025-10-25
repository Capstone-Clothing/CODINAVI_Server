package com.example.CODINAVI.service.weather;

import com.example.CODINAVI.domain.temp.TempInfo;
import com.example.CODINAVI.domain.temp.TempInfoReposiotry;
import com.example.CODINAVI.dto.request.weather.WeatherCodiRequest;
import com.example.CODINAVI.dto.request.weather.WeatherRequest;
import com.example.CODINAVI.dto.response.weather.*;
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
    private final TempInfoReposiotry tempInfoReposiotry;

    public WeatherService(TempInfoReposiotry tempInfoReposiotry) {
        this.tempInfoReposiotry = tempInfoReposiotry;
    }

    public WeatherInfoResponse getWeatherInfo(WeatherRequest request) {

        LocalDateTime nowTime = LocalDateTime.now();
        String formatedNow = nowTime.format(DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss"));
        String subStringNowDay = formatedNow.substring(0, 8);
        String subStringNowTime = formatedNow.substring(9, 11);
        String base_time = timeChange(subStringNowTime);
        String base_date;

        if (base_time.equals("2300")) {
            base_date = String.valueOf(Integer.parseInt(subStringNowDay) - 1);
        } else {
            base_date = subStringNowDay;
        }

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
                                        .queryParam("numOfRows", 507)
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
        List<String> dateList = new ArrayList<>();
        List<String> humidityList = new ArrayList<>();
        List<String> precipitationTypeList = new ArrayList<>();
        List<String> precipitationList = new ArrayList<>();
        List<String> precipitationProbabilityList = new ArrayList<>();

        for (int i = 0; i < itemList.length(); i++) {

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

                dateList.add(fcstDate);
                timeList.add(fcstTime);
                tempList.add(fcstValue);
            }

            if (category.equals("REH")) {
                humidityList.add(fcstValue);
            }

            if (category.equals("PCP")) {
                precipitationList.add(fcstValue);
            }

            if (category.equals("POP")) {
                precipitationProbabilityList.add(fcstValue);
            }

            if (category.equals("PTY")) {
                if (fcstValue.equals("0")) {
                    precipitationTypeList.add("없음");
                } else if (fcstValue.equals("1")) {
                    precipitationTypeList.add("비");
                } else if (fcstValue.equals("2")) {
                    precipitationTypeList.add("비 또는 눈");
                } else if (fcstValue.equals("3")) {
                    precipitationTypeList.add("눈");
                } else {
                    precipitationTypeList.add("소나기");
                }
            }
        }

        String tempMinMaxResponse =
                webClient.get()
                        .uri(uriBuilder ->
                                uriBuilder.path("/1360000/VilageFcstInfoService_2.0/getVilageFcst")
                                        .queryParam("serviceKey", "cQvLnSjhJGqaRDQw3oWGS3PLYZ%2F0mK2hywjRA07%2F1Gc455UdpgXjjyTwTQJxQcI52xi6nl%2By9XgDlhQEF5o9Uw%3D%3D")
                                        .queryParam("pageNo", 1)
                                        .queryParam("numOfRows", 507)
                                        .queryParam("dataType", "JSON")
                                        .queryParam("base_date", subStringNowDay)
                                        .queryParam("base_time", "0200")
                                        .queryParam("nx", changeLatAndLonToCoordinate(request.getLat(), request.getLon()).getX())
                                        .queryParam("ny", changeLatAndLonToCoordinate(request.getLat(), request.getLon()).getY())
                                        .build())
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();

        JSONObject jsonObject2 = new JSONObject(tempMinMaxResponse);
        JSONObject response2 = jsonObject2.getJSONObject("response").getJSONObject("body");
        JSONObject items2 = response2.getJSONObject("items");
        JSONArray itemList2 = items2.getJSONArray("item");

        String lowTemp = "";
        String highTemp = "";

        log.info("checkItemList2 = {}", itemList2.toString());
        for (int i = 0; i < itemList2.length(); i++) {

            JSONObject item = itemList2.getJSONObject(i);
            String fcstValue = item.getString("fcstValue");
            String category = item.getString("category");
            String fcstDate = item.getString("fcstDate");

            if (category.equals("TMN") && fcstDate.equals(subStringNowDay)) {
                lowTemp = fcstValue;
            }

            if (category.equals("TMX") && fcstDate.equals(subStringNowDay)) {
                highTemp = fcstValue;
            }
        }

        List<InfoFromWeatherResponse> infoFromWeatherResponse = new ArrayList<>();
        List<InfoFromTimeResponse> infoFromTimeResponses = new ArrayList<>();

        for (int i = 0; i < weatherList.size(); i++) {
            infoFromTimeResponses.add(new InfoFromTimeResponse(timeList.get(i), weatherList.get(i), tempList.get(i), humidityList.get(i), precipitationTypeList.get(i), precipitationList.get(i), precipitationProbabilityList.get(i)));
        }

        for (int i = 0; i < dateList.size(); i++) {
            infoFromWeatherResponse.add(new InfoFromWeatherResponse(dateList.get(i), lowTemp, highTemp, infoFromTimeResponses.get(i)));
        }

        WeatherInfoResponse weatherInfoResponse = new WeatherInfoResponse(infoFromWeatherResponse);

        return weatherInfoResponse;
    }

    public WeatherCodiResponse getRecInfo(WeatherCodiRequest request) {

        TempInfo tempInfo;
        log.info("checkRequest={}", request.getGender());
        log.info("checkRequest={}", request.getTemp());

        if (request.getTemp() >= 28.0) {
            tempInfo = tempInfoReposiotry.findByGenderAndMinTemp(request.getGender(), 28.0);
        } else if (request.getTemp() >= 23.0 && request.getTemp() <= 27.9) {
            tempInfo = tempInfoReposiotry.findByGenderAndMinTemp(request.getGender(), 23.0);
        } else if (request.getTemp() >= 20.0 && request.getTemp() <= 22.9) {
            tempInfo = tempInfoReposiotry.findByGenderAndMinTemp(request.getGender(), 20.0);
        } else if (request.getTemp() >= 17.0 && request.getTemp() <= 19.9) {
            tempInfo = tempInfoReposiotry.findByGenderAndMinTemp(request.getGender(), 17.0);
        } else if (request.getTemp() >= 12.0 && request.getTemp() <= 16.9) {
            tempInfo = tempInfoReposiotry.findByGenderAndMinTemp(request.getGender(), 12.0);
        } else if (request.getTemp() >= 9.0 && request.getTemp() <= 11.9) {
            tempInfo = tempInfoReposiotry.findByGenderAndMinTemp(request.getGender(), 9.0);
        } else if (request.getTemp() >= 5.0 && request.getTemp() <= 8.9) {
            tempInfo = tempInfoReposiotry.findByGenderAndMinTemp(request.getGender(), 5.0);
        } else {
            tempInfo = tempInfoReposiotry.findByGenderAndMinTemp(request.getGender(), -100.0);
        }

        WeatherCodiResponse response = new WeatherCodiResponse(tempInfo.getCodi(), tempInfo.getClothRec());
        return response;
    }

    public LatAndLonTransferResponse changeLatAndLonToCoordinate(Double lat, Double lon) {

        LatAndLonTransferResponse response = new LatAndLonTransferResponse(0, 0);

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
        double x = Math.floor(ra * Math.sin(theta) + XO + 0.5);
        double y = Math.floor(ro - ra * Math.cos(theta) + YO + 0.5);

        response.setX((int) x);
        response.setY((int) y);

        return response;
    }

    public String timeChange(String time) {
        // 현재 시간에 따라 데이터 시간 설정(3시간 마다 업데이트) //
        switch (time) {

            case "00":
            case "01":
            case "02":
                time = "2300";
                break;
            case "03":
            case "04":
            case "05":
                time = "0200";
                break;
            case "06":
            case "07":
            case "08":
                time = "0500";
                break;
            case "09":
            case "10":
            case "11":
                time = "0800";
                break;
            case "12":
            case "13":
            case "14":
                time = "1100";
                break;
            case "15":
            case "16":
            case "17":
                time = "1400";
                break;
            case "18":
            case "19":
            case "20":
                time = "1700";
                break;
            case "21":
            case "22":
            case "23":
                time = "2000";
                break;

        }
        return time;
    }
}

