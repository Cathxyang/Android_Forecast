package com.example.forecast.model;

public class Weather {

    public String firstWeather;
    public String secondWeather;

    public Weather(String firstWeather, String secondWeather) {
        this.firstWeather = firstWeather;
        this.secondWeather = secondWeather;
    }

    public String getFirstWeather() {
        return firstWeather;
    }

    public String getSecondWeather() {
        return secondWeather;
    }
}
