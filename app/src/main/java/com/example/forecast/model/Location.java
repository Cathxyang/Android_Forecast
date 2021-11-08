package com.example.forecast.model;

public class Location {
    public String city;
    public String adcode;

    public Location(String city, String adcode) {
        this.city = city;
        this.adcode = adcode;
    }

    public String getCity() {
        return city;
    }

    public String getAdcode() {
        return adcode;
    }
}
