package com.example.forecast.model;

public class SimpleWeather {

    public String city;
    public String temperature;

    public SimpleWeather(String city, String temperature) {
        this.city = city;
        this.temperature = temperature;
    }

    public String getCity() {
        return city;
    }

    public String getTemperature() {
        return temperature;
    }
}
