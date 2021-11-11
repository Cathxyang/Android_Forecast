package com.example.forecast.controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.forecast.R;
import com.example.forecast.db.MyDatabaseHelper;
import com.example.forecast.model.BaseWeather;
import com.example.forecast.util.DownloadManager;

import java.util.ArrayList;

public class MoreActivity extends AppCompatActivity {

    private ArrayList<BaseWeather> favoriteList = new ArrayList<>();
    private MyDatabaseHelper dbHelper;
    private ArrayList<String> adcodeData = new ArrayList<>();
    private ArrayList<String> cityData = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);


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


    public void onResume() {

        super.onResume();

        //favoriteList数据
        adcodeData.clear();
        favoriteList.clear();
        FavoriteAdapter adapter = new FavoriteAdapter(MoreActivity.this,
                R.layout.simple_item, favoriteList);
        ListView favorite = (ListView) findViewById(R.id.favorite);
        favorite.setAdapter(adapter);


        //查看所有关注的城市
        DownloadManager requestDownloadManager = new DownloadManager();
        dbHelper = new MyDatabaseHelper(this, null, null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("Forecast", null, null, null,
                null, null, null);
        BaseWeather baseWeather;
        ArrayList<String> temFollow = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range")
                String adcode = cursor.getString(cursor.getColumnIndex("adcode"));
                @SuppressLint("Range")
                String city = cursor.getString(cursor.getColumnIndex("city"));
                ArrayList<String> temCurrent = new ArrayList();
                //得到当前温度
                requestDownloadManager.sendBaseWeatherRequestWithOkHttp(new DownloadManager.BaseWeatherCallBack() {
                    @Override
                    public void finishBaseWeather(BaseWeather baseWeather) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                temFollow.add(baseWeather.temperature);
                                cityData.add(baseWeather.city);
                                BaseWeather weather = new BaseWeather(baseWeather.city, baseWeather.temperature, "","");
                                favoriteList.add(weather);
                                adcodeData.add(adcode);

                                //更新当前cell
                                View view = favorite.getChildAt(adcodeData.indexOf(adcode));
                                favorite.getAdapter().getView(adcodeData.indexOf(adcode), view, favorite);
                                adapter.notifyDataSetChanged();
                                favorite.invalidate();
                            }
                        });
                    }
                }, adcode);



            } while (cursor.moveToNext());
        }
        cursor.close();


        //短摁查看
        favorite.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data = adcodeData.get(position);
                String city = cityData.get(position);
                Intent intent = new Intent(MoreActivity.this, WeatherActivity.class);
                intent.putExtra("extra_data", data);
                intent.putExtra("extra_city", city);
                startActivity(intent);
            }
        });


        //长摁删除
        favorite.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //移除数据库中数据
                db.execSQL("delete from Forecast where adcode = ?",
                        new String[]{String.valueOf(adcodeData.get(position))});
                //移除ArrayList中数据
                adcodeData.remove(adcodeData.get(position));
                cityData.remove(String.valueOf(cityData.get(position)));
                favoriteList.remove(position);
                //重新加载当前adapter
                favorite.setAdapter(adapter);

                //更新当前整个列表
                adapter.notifyDataSetChanged();
                favorite.invalidate();
                return true;
            }
        });


    }

}