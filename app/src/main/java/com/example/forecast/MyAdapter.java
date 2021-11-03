package com.example.forecast;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends ArrayAdapter<SimpleWeather> {

    private int id;

    public MyAdapter(Context context, int textViewResourceId, List<SimpleWeather> object){
        super(context, textViewResourceId, object);
        id = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        SimpleWeather simpleWeather = getItem(position);
        @SuppressLint("ViewHolder") View view = LayoutInflater.from(getContext()).inflate(id, parent, false);
        TextView city = (TextView) view.findViewById(R.id.city);
        TextView temperature = (TextView) view.findViewById(R.id.temperature);
        city.setText(simpleWeather.getCity());
        temperature.setText(simpleWeather.getTemperature());
        return view;
    }
}
