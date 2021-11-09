package com.example.forecast.model;

public class BaseWeather {

    public String city;
    public String temperature;
    public String humidity;

    public BaseWeather(String city, String temperature, String humidity) {
        this.city = city;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public String getCity() {
        return city;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getHumidity() {
        return humidity;
    }
}
