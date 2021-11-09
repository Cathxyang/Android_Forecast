package com.example.forecast.model;

public class AllWeather {

    public String date;
    public String highTem;
    public String lowTem;

    public AllWeather(String date, String highTem, String lowTem) {
        this.date = date;
        this.highTem = highTem;
        this.lowTem = lowTem;
    }

    public String getDate() { return date; }

    public String getHighTem() {
        return highTem;
    }

    public String getLowTem() {
        return lowTem;
    }
}
