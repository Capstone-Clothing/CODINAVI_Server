package com.example.CODINAVI.controller.color;

import java.time.LocalDate;

public class TestRequest {
    private String name;
    private LocalDate date;
    private int age;

    public TestRequest(String name, LocalDate date, int age) {
        this.name = name;
        this.date = date;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getAge() {
        return age;
    }
}
