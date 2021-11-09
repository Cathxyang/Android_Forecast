package com.example.forecast.util;

import com.example.forecast.model.AllWeather;
import com.example.forecast.model.BaseWeather;
import com.example.forecast.model.Location;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadManager {

    public String temperature;
    public String humidity;

    public interface BaseWeatherCallBack {
        void finishBaseWeather(BaseWeather baseWeather);
    }

    public interface AllWeatherCallBack {
        void finishAllWeather(ArrayList arrayList);
    }

    public interface LocationCallBack {
        void finishLocation(ArrayList arrayList);
    }


    //发送地点请求
    public void sendLocationRequestWithOkHttp(LocationCallBack callBack, String city) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url(
                            "https://restapi.amap.com/v3/config/district?keywords=" + city +
                                    "&subdistrict=1&key=" + "a9b3b5acd94b6490390299b2a12bb185").build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    callBack.finishLocation(parseLocationJSONWithJSONObject(responseData));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    //解析地点需要的内容
    public ArrayList<Location> parseLocationJSONWithJSONObject(String jsonData) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject object = jsonObject.getJSONArray("districts").getJSONObject(0);
            JSONArray jsonArray = object.getJSONArray("districts");
            ArrayList location = new ArrayList<Location>();
            for (int i = 0; i < jsonArray.length(); i++) {
                location.add(new Location(jsonArray.getJSONObject(i).getString("name"),
                        jsonArray.getJSONObject(i).getString("adcode")));
            }
            return location;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    //发送当前天气请求
    public void sendBaseWeatherRequestWithOkHttp(BaseWeatherCallBack callBack, String city) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url(
                            "https://restapi.amap.com/v3/weather/weatherInfo?city=" + city +
                                    "&extensions=base&key=" + "a9b3b5acd94b6490390299b2a12bb185").build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    callBack.finishBaseWeather(parseBaseWeatherJSONWithJSONObject(responseData));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    //解析基础天气需要的内容
    public BaseWeather parseBaseWeatherJSONWithJSONObject(String jsonData) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject object = jsonObject.getJSONArray("lives").getJSONObject(0);
            return new BaseWeather(object.getString("city"),
                    object.getString("temperature"),
                    object.getString("humidity"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    //发送所有天气请求
    public void sendAllWeatherRequestWithOkHttp(AllWeatherCallBack callBack, String city) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url(
                            "https://restapi.amap.com/v3/weather/weatherInfo?city=" + city +
                                    "&extensions=all&key=" + "a9b3b5acd94b6490390299b2a12bb185").build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    callBack.finishAllWeather(parseAllWeatherJSONWithJSONObject(responseData));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    //解析所有天气需要的内容
    public ArrayList<AllWeather> parseAllWeatherJSONWithJSONObject(String jsonData) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject object = jsonObject.getJSONArray("forecasts").getJSONObject(0);
            JSONArray jsonArray = object.getJSONArray("casts");
            ArrayList weather = new ArrayList<AllWeather>();
            for (int i = 0; i < jsonArray.length(); i++) {
                weather.add(new AllWeather(jsonArray.getJSONObject(i).getString("date"),
                        jsonArray.getJSONObject(i).getString("daytemp"),
                        jsonArray.getJSONObject(i).getString("nighttemp")));
            }
            return weather;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
