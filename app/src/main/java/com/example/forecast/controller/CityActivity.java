package com.example.forecast.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.forecast.R;
import com.example.forecast.model.Location;
import com.example.forecast.util.DownloadManager;

import java.util.ArrayList;

public class CityActivity extends AppCompatActivity {

    private ArrayList<String> city = new ArrayList();
    private ArrayList<String> adcode = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        //得到当前省
        Intent intent = getIntent();
        String province = intent.getStringExtra("extra_data");

        //创建listview
        ArrayAdapter adapter = new ArrayAdapter<>(CityActivity.this,
                android.R.layout.simple_list_item_1, city);
        ListView listView = (ListView) findViewById(R.id.city);
        listView.setAdapter(adapter);

        //显示当前省份所有市区
        DownloadManager requestDownloadManager = new DownloadManager();
        requestDownloadManager.sendLocationRequestWithOkHttp(new DownloadManager.LocationCallBack() {
            @Override
            public void finishLocation(ArrayList arrayList) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < arrayList.size(); i++) {
                            city.add(((Location) arrayList.get(i)).getCity());
                            adcode.add(((Location) arrayList.get(i)).getAdcode());
                        }
                        adapter.notifyDataSetChanged();
                        listView.invalidate();
                    }
                });
            }
        }, province);

        //跳转到当前市区的天气
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data = adcode.get(position);
                String cityData = city.get(position);
                Intent intent = new Intent(CityActivity.this, FavoriteActivity.class);
                intent.putExtra("extra_data", data);
                intent.putExtra("extra_city", cityData);
                startActivity(intent);
            }
        });
    }
}