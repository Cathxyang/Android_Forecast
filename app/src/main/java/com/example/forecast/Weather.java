package com.example.forecast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Weather extends AppCompatActivity {

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

        //更新按钮


        //更多显示按钮
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Weather.this, More.class);
                startActivity(intent);
            }
        });

        //显示城市
        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Weather.this, Province.class);
                startActivity(intent);
            }
        });

        //显示温度


        //显示湿度

    }

}