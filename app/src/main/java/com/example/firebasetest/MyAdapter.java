package com.example.firebasetest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<Data> Data;

    public MyAdapter(Context context, ArrayList<Data> data) {
        mContext = context;
        Data = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return Data.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Data getItem(int position) {
        return Data.get(position);
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.recommendlist, null);

        ImageView image = (ImageView)view.findViewById(R.id.image);
        TextView name = (TextView)view.findViewById(R.id.name);
        TextView material = (TextView)view.findViewById(R.id.material);

        Glide.with(view).load(Data.get(position).getImage()).into(image);
        name.setText(Data.get(position).getName());
        String material_list ="";
        int i= 1;
        int len = Data.get(position).getMaterial().size();
        for(String e: Data.get(position).getMaterial()){
            material_list += e;
            if(i < len)
                material_list += ",";
            i++;
        }
        material.setText(material_list);

        return view;
    }
}
