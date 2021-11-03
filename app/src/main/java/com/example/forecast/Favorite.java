package com.example.forecast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Favorite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        //按钮实例化和文本框
        Button cancel = (Button) findViewById(R.id.cancel);
        Button follow = (Button) findViewById(R.id.follow);
        TextView city = (TextView) findViewById(R.id.city);
        TextView temperature = (TextView) findViewById(R.id.temperature);
        TextView humidity = (TextView) findViewById(R.id.humidity);


        //取消
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Favorite.this, Weather.class);
                startActivity(intent);
            }
        });

        //添加关注
        follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }
}