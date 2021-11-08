package com.example.forecast.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.forecast.R;
import com.example.forecast.model.SimpleWeather;

import java.util.ArrayList;
import java.util.List;

public class MoreActivity extends AppCompatActivity {

    private List<SimpleWeather> favoriteList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        //favoriteList数据
        FavoriteAdapter adapter = new FavoriteAdapter(MoreActivity.this, R.layout.simple_item, favoriteList);
        ListView favorite = (ListView) findViewById(R.id.favorite);
        favorite.setAdapter(adapter);

        //点击文本框
        EditText search = (EditText) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoreActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });


    }
}