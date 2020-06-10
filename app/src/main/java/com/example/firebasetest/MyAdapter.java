package com.example.firebasetest;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;
    ArrayList<Data> Data;
    ImageView image;
    TextView name;
    TextView material;
    Bitmap bm;

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
        View view = mLayoutInflater.inflate(R.layout.listelement, null);
        image = view.findViewById(R.id.image);
        name = view.findViewById(R.id.name);
        material = view.findViewById(R.id.material);
        Glide.with(view).load(Data.get(position).getImage()).into(image);
        name.setText(Data.get(position).getName());
        material.setText(Data.get(position).getMaterial());

        return view;
    }
}
