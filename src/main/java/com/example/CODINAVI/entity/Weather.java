package com.example.CODINAVI.entity;

public class Weather {
    private Double temp;
    private String tempInfo;
    private String test;

    public void setTemp(Double temp) {
        this.temp = temp;
    }
    public Double getTemp() {
        return temp;
    }
    public String idk(){return "hihihihi";}
    public String getClothInfo() {

        if (temp >= 28) {
            tempInfo = "상의는 민소매, 반팔을 추천해드리며 하의는 짧은 치마, 린넨 옷, 반바지를 추천드립니다.";
        }
        else if (23.0<=temp && temp<=27.0) {
            tempInfo = "상의는 얇은 셔츠, 반팔을 추천해드리며 하의는 반바지, 면바지를 추천드립니다.";
        }
        else if (20.0<=temp && temp<=22.0) {
            tempInfo = "상의는 블라우스, 긴팔티를 추천해드리며 하의는 면바지, 슬랙스를 추천드립니다.";
        }
        else if (17.0<=temp && temp<=19.0) {
            tempInfo = "상의는 얇은 가디건, 니트, 맨투맨, 후드를 추천해드리며 하의는 긴 바지를 추천드립니다.";
        }
        else if (12.0<=temp && temp<=16.0) {
            tempInfo = "상의는 자켓 또는 청자켓, 가디건, 니트를 추천해드리며 하의는 스타킹, 청바지를 추천드립니다.";
        }
        else if (9.0<=temp && temp<=11.0) {
            tempInfo = "상의는 트렌치 코트, 야상, 점퍼를 추천해드리며 하의는 스타킹, 기모바지를 추천드립니다.";
        }
        else if (5.0<=temp && temp<=8.0) {
            tempInfo = "상의는 울 코트 또는 가죽 옷과 히트텍을 추천해드리며 하의 또한 기모가 들어간 바지를 추천드립니다.";
        }
        else {
            tempInfo = "상의는 패딩, 두꺼운 코트, 누빔 옷, 기모가 들어간 소재, 그리고 목도리를 걸치시는 것을 추천드립니다.";
        }
        return tempInfo;
    }
}
