package com.example.pianoforkid.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.firebase.database.PropertyName;

@Entity(tableName = "user_table")
public class User {
    @PrimaryKey
    @NonNull
    @PropertyName("uid")
    public String userId;
    @PropertyName("email")
    public String email;
    @PropertyName("name")
    public String name;
    @PropertyName("score")
    public int score;
    public User(String userId, String name, String email, int score){
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.score = score;
    }
    public User(){
        score = 0;
        userId = "null";
    }
}
