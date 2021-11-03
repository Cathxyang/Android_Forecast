package com.example.forecast;

public class SimpleWeather {

    private String city;
    private String temperature;

    public SimpleWeather(String city, String temperature){
        this.city = city;
        this.temperature = temperature;
    }

    public String getCity(){
        return city;
    }

    public String getTemperature(){
        return temperature;
    }
}
