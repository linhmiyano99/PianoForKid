package com.example.pianoforkid.data.model;

import androidx.room.Entity;

import com.google.firebase.database.PropertyName;

@Entity(tableName = "user_table")
public class User {
    @PropertyName("userId")
    public String userId;
    @PropertyName("identifier")
    public String identifier;
    @PropertyName("name")
    public String name;
    @PropertyName("score")
    public int score;
    public User(String userId, String name, String identifier, int score){
        this.userId = userId;
        this.name = name;
        this.identifier = identifier;
        this.score = score;
    }
}
