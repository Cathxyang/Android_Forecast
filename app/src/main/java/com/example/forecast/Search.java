package com.example.forecast;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;

public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //隐藏ActionBar
        if (this.getSupportActionBar() != null) {
            this.getSupportActionBar().hide();
        }
//        ActionBar actionBar = getActionBar();
//        actionBar.hide();
    }
}