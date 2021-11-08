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

public class FavoriteActivity extends AppCompatActivity {

    public ArrayList<Weather> hlTem = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        //得到当前区号
        Intent intent = getIntent();
        String adcode = intent.getStringExtra("extra_data");
        String cityData = intent.getStringExtra("extra_city");

        //按钮实例化和文本框
        Button cancel = (Button) findViewById(R.id.cancel);
        Button follow = (Button) findViewById(R.id.follow);
        TextView city = (TextView) findViewById(R.id.city);
        TextView temperature = (TextView) findViewById(R.id.temperature);
        TextView humidity = (TextView) findViewById(R.id.humidity);
        ListView allTem = (ListView) findViewById(R.id.allTem);


        //取消
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FavoriteActivity.this, WeatherActivity.class);
                startActivity(intent);
            }
        });

        //添加关注
        follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FavoriteActivity.this, MoreActivity.class);
                startActivity(intent);

            }
        });

        //显示地区
        city.setText(cityData);

        //显示当前温度、湿度
        DownloadManager requestDownloadManager = new DownloadManager();
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
        },adcode);

        //显示最高温度和最低温度
        AllWeatherAdapter adapter = new AllWeatherAdapter(FavoriteActivity.this,
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
        }, adcode);
    }
}