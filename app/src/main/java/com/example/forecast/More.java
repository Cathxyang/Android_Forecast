package com.example.forecast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class More extends AppCompatActivity {

    private List<SimpleWeather> favoriteList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        //favoriteList数据
        MyAdapter adapter = new MyAdapter(More.this, R.layout.simple_item, favoriteList);
        ListView favorite = (ListView) findViewById(R.id.favorite);
        favorite.setAdapter(adapter);

        //点击文本框
        EditText search = (EditText) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(More.this, Search.class);
                startActivity(intent);
            }
        });


    }
}