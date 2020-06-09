package com.example.firebasetest;

import java.util.ArrayList;

public class Data {
    private String image_url;
    private String name;
    private ArrayList<String> material;

    public Data(String image_url, String name, ArrayList<String> material){
        this.image_url = image_url;
        this.name = name;
        this.material = material;
    }

    public String getImage()
    {
        return this.image_url;
    }

    public String getName()
    {
        return this.name;
    }

    public ArrayList<String> getMaterial()
    {
        return this.material;
    }
}
