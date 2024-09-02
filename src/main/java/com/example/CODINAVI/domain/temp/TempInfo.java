package com.example.CODINAVI.domain.temp;

import jakarta.persistence.*;

@Entity
public class TempInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String gender;

    private Double minTemp;

    private Double maxTemp;

    @Column(nullable = false)
    private String codi;

    @Column(nullable = false)
    private String clothRec;

    public TempInfo() {
    }

    public TempInfo(String gender, Double minTemp, Double maxTemp, String codi, String clothRec) {
        this.gender = gender;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.codi = codi;
        this.clothRec = clothRec;
    }

    public String getGender() {
        return gender;
    }

    public Double getMinTemp() {
        return minTemp;
    }

    public Double getMaxTemp() {
        return maxTemp;
    }

    public String getCodi() {
        return codi;
    }

    public String getClothRec() {
        return clothRec;
    }
}
