package com.example.forecast.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.forecast.R;

public class SearchActivity extends AppCompatActivity {
    EditText search;
    public String searchInformation = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //隐藏ActionBar
        if (this.getSupportActionBar() != null) {
            this.getSupportActionBar().hide();
        }

        //直接显示键盘
        search = findViewById(R.id.search);
        search.requestFocus();

        //查询城市或编号
        Button confirm = (Button) findViewById(R.id.confirm_button);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchInformation = search.getText().toString();
                if(!searchInformation.equals("")){
                    Intent intent = new Intent(SearchActivity.this, FavoriteActivity.class);
                    intent.putExtra("extra_data", searchInformation);
                    startActivity(intent);
                }else {
                    Toast.makeText(search.getContext(),"输入编号没有对应的区号", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}