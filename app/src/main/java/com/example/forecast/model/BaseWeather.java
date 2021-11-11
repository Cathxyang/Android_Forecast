package com.example.forecast.model;

public class BaseWeather {

    public String city;
    public String temperature;
    public String humidity;
    public String reporttime;

    public BaseWeather(String city, String temperature, String humidity, String reporttime) {
        this.city = city;
        this.temperature = temperature;
        this.humidity = humidity;
        this.reporttime = reporttime;
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

    public String getReporttime() { return reporttime; }
}
