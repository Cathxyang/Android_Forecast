package com.example.forecast.controller;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
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
    public ArrayList<String> adcode = null;
    private HistoryDatabaseHelper dbHelper = new HistoryDatabaseHelper(
            this, null, null, 1);


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
                    db = dbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("adcode", searchInformation);
                    db.insert("History", null, values);
                } else {
                    Toast.makeText(search.getContext(), "输入编号没有对应的区号", Toast.LENGTH_SHORT).show();
                }
            }
        });


        //判断数据库是否超过三条，如果超过则删除id最小的一条
        Cursor cursor = db.query("History", null, null, null,
                null, null, null);
        if(getRowCount() > 3){
//            int id = cursor.moveToFirst();
//            //移除ArrayList中数据
//            adcode.remove();
//            delete from History where id = ?
        }

        //将现有的编号插入adcode


        //历史记录
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SearchActivity.this,
                android.R.layout.simple_list_item_1, adcode);
        ListView listView = (ListView) findViewById(R.id.history);
        listView.setAdapter(adapter);
    }


    //计算数据库中的行数
    public int getRowCount() {
        String sql = "select count(*) from Histiry";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count;
    }


}