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

public class ProvinceActivity extends AppCompatActivity {

    private ArrayList province;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_province);

        province = new ArrayList<String>();
        ArrayAdapter adapter = new ArrayAdapter<ArrayList>(ProvinceActivity.this,
                android.R.layout.simple_list_item_1, province);
        ListView listView = (ListView) findViewById(R.id.province);
        listView.setAdapter(adapter);

        //显示中国所有省份
        DownloadManager requestDownloadManager = new DownloadManager();
        requestDownloadManager.sendLocationRequestWithOkHttp(new DownloadManager.LocationCallBack() {
            @Override
            public void finishLocation(ArrayList arrayList) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < arrayList.size(); i++) {
                            province.add(((Location) arrayList.get(i)).getCity());
                        }
                        adapter.notifyDataSetChanged();
                        listView.invalidate();
                    }
                });
            }
        }, "中国");

        //跳转到当前省份的市区
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data = (String) province.get(position);
                Intent intent = new Intent(ProvinceActivity.this, CityActivity.class);
                intent.putExtra("extra_data", data);
                startActivity(intent);
            }
        });

    }
}