package com.example.forecast.controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.forecast.R;
import com.example.forecast.model.BaseWeather;

import java.util.List;

public class FavoriteAdapter extends ArrayAdapter<BaseWeather> {

    private int id;

    public FavoriteAdapter(Context context, int textViewResourceId, List<BaseWeather> object){
        super(context, textViewResourceId, object);
        id = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        BaseWeather simpleAllWeather = getItem(position);
        @SuppressLint("ViewHolder") View view = LayoutInflater.from(getContext()).inflate(id,
                parent, false);
        TextView city = (TextView) view.findViewById(R.id.city);
        TextView temperature = (TextView) view.findViewById(R.id.temperature);
        city.setText(simpleAllWeather.getCity());
        temperature.setText(simpleAllWeather.getTemperature());
        return view;
    }
}
