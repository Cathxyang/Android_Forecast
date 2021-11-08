package com.example.forecast.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.forecast.R;
import com.example.forecast.model.Weather;
import com.example.forecast.util.DownloadManager;

import java.util.ArrayList;

public class WeatherActivity extends AppCompatActivity {

    public ArrayList<Weather> hlTem = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        //实例化页面上的按钮和文本框
        Button update = (Button) findViewById(R.id.update);
        Button more = (Button) findViewById(R.id.more);
        TextView city = (TextView) findViewById(R.id.city);
        TextView temperature = (TextView) findViewById(R.id.temperature);
        TextView humidity = (TextView) findViewById(R.id.humidity);
        ListView allTem = (ListView) findViewById(R.id.allTem);

        //更新按钮


        //更多显示按钮
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeatherActivity.this, MoreActivity.class);
                startActivity(intent);
            }
        });

        //点选选择城市
        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeatherActivity.this, ProvinceActivity.class);
                startActivity(intent);
            }
        });

        //显示城市
        DownloadManager requestDownloadManager = new DownloadManager();


        //显示当前温度、湿度
        requestDownloadManager.sendBaseWeatherRequestWithOkHttp(new DownloadManager.BaseWeatherCallBack() {
            @Override
            public void finishBaseWeather(Weather weather) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        temperature.setText("当前温度：" + weather.firstWeather + "°" + "\n");
                        humidity.setText("当前湿度：" + weather.secondWeather);
                    }
                });
            }
        }, "110101");

        //显示最高温度和最低温度
        AllWeatherAdapter adapter = new AllWeatherAdapter(WeatherActivity.this,
                R.layout.all_item, hlTem);
        allTem.setAdapter(adapter);
        requestDownloadManager.sendAllWeatherRequestWithOkHttp(new DownloadManager.AllWeatherCallBack() {
            @Override
            public void finishAllWeather(ArrayList arrayList) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hlTem.addAll(arrayList);
                        adapter.notifyDataSetChanged();
                        allTem.invalidate();
                    }
                });
            }
        }, "110101");

    }


}