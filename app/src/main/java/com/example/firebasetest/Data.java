package com.example.firebasetest;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Data{
    private String image_url;
    private String name;
    private String material;
    private String cooking;

    public Data(String image_url, String name, String material, String cooking){
        this.image_url = image_url;
        this.name = name;
        this.material = material;
        this.cooking = cooking;
    }


    public String getImage()
    {
        return this.image_url;
    }

    public String getName()
    {
        return this.name;
    }

    public String getMaterial()
    {
        return this.material;
    }

    public String getCooking() { return this.cooking; }

}
