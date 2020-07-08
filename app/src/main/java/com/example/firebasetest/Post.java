package com.example.firebasetest;

public class Post {
    private String UID;
    private Integer PID;
    private Integer post_like;
    private Integer post_star;
    private String recipe_name;
    private String ingrd;
    private String cooking;
    private String qnt;
    public Post(String iUID,Integer iPID,String ipost_name,Integer ipost_like,Integer ipost_star,String irecipe_name,String iingrd,String icooking,String iqnt){
        UID = iUID;
        PID = iPID;
        post_like = ipost_like;
        post_star = ipost_star;
        recipe_name = irecipe_name;
        ingrd = iingrd;
        cooking = icooking;
        qnt = iqnt;
    }
}
