package com.example.firebasetest;

import java.util.ArrayList;

public class Data {
    private int image;
    private String name;
    private String material;

    public Data(int image, String name, String material){
        this.image = image;
        this.name = name;
        this.material = material;
    }

    public int getImage()
    {
        return this.image;
    }

    public String getName()
    {
        return this.name;
    }

    public String getMaterial()
    {
        return this.material;
    }
}
