package com.example.pianoforkid.data.model;

import com.google.firebase.database.PropertyName;

public class User {
    @PropertyName("email")
    public String email;
    @PropertyName("name")
    public String name;
    @PropertyName("photo")
    public String photo;
    @PropertyName("score")
    public int score;
    public User(String email, String name, String photo, int score){
        this.email = email;
        this.name = name;
        this.photo = photo;
        this.score = score;
    }
}
