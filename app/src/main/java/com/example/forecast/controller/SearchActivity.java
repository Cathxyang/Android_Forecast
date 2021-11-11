package com.example.forecast.controller;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.forecast.R;
import com.example.forecast.db.HistoryDatabaseHelper;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    EditText search;
    SQLiteDatabase db;
    int rowCount = 0;
    public String searchInformation = null;
    public ArrayList<String> adcode = new ArrayList<>();
    public HistoryDatabaseHelper dbHelper = new HistoryDatabaseHelper(
            this, null, null, 1);


    @SuppressLint("Range")
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


        //查询城市编号
        db = dbHelper.getWritableDatabase();
        Button confirm = (Button) findViewById(R.id.confirm_button);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchInformation = search.getText().toString();
                if (!searchInformation.equals("")) {
                    Intent intent = new Intent(SearchActivity.this, FavoriteActivity.class);
                    intent.putExtra("extra_data", searchInformation);
                    startActivity(intent);

                    //插入历史记录
                    ContentValues values = new ContentValues();
                    values.put("adcode", searchInformation);
                    db.insert("History", null, values);
                } else {
                    Toast.makeText(search.getContext(), "请输入编号", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @SuppressLint("Range")
    protected void onResume() {
        super.onResume();


        //判断数据库是否超过四条，如果超过则删除id最小的一条
        Cursor cursor = db.query("History", null, null, null,
                null, null, null);
        while (getRowCount() > 4) {
            cursor.moveToFirst();
            @SuppressLint("Range")
            String id = cursor.getString(cursor.getColumnIndex("id"));
            db.delete("History", "id = ?", new String[]{id});
        }


        //清空历史
        adcode.clear();
        //将现有的编号插入adcode
        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            adcode.add(cursor.getString(cursor.getColumnIndex("adcode")));
        }

        //显示历史记录
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SearchActivity.this,
                android.R.layout.simple_list_item_1, adcode);
        ListView listView = (ListView) findViewById(R.id.history);
        listView.setAdapter(adapter);


        //查询历史
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String adcodeData = adcode.get(position);
                Intent intent = new Intent(SearchActivity.this,
                        FavoriteActivity.class);
                intent.putExtra("extra_data", adcodeData);
                startActivity(intent);
            }
        });


    }


    //计算数据库中的行数
    @SuppressLint("Range")
    public int getRowCount() {
        String sql = "select * from History";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        int count = 0;
        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            count += 1;
        }
        cursor.close();
        return count;
    }
}