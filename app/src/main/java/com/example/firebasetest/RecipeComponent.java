package com.example.firebasetest;

import java.util.ArrayList;

public class RecipeComponent {
    int R_id;
    String name;
    String image_url;
    ArrayList<String> material;
    public RecipeComponent(int R_id, String name, String image_url, ArrayList<String> material){
        this.R_id = R_id;
        this.name = name;
        this.image_url = image_url;
        this.material = material;
    }
}
