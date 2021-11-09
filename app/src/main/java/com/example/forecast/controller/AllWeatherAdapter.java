package com.example.forecast.controller;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.forecast.R;
import com.example.forecast.model.AllWeather;

import java.util.ArrayList;


public class AllWeatherAdapter extends ArrayAdapter<AllWeather> {
    private int id;

    public AllWeatherAdapter(Context context, int textViewResourceId, ArrayList<AllWeather> object) {
        super(context, textViewResourceId, object);
        id = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AllWeather allWeather = getItem(position);
        @SuppressLint("ViewHolder") View view = LayoutInflater.from(getContext()).inflate(id,
                parent, false);
        TextView city = (TextView) view.findViewById(R.id.city);
        TextView highTem = (TextView) view.findViewById(R.id.highTem);
        TextView lowTem = (TextView) view.findViewById(R.id.lowTem);
        city.setText(allWeather.getDate());
        lowTem.setText(allWeather.getLowTem());
        highTem.setText(allWeather.getHighTem());
        return view;
    }
}
