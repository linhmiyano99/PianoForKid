package com.example.pianoforkid.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.firebase.database.PropertyName;

@Entity(tableName = "user_table")
public class User {
    @PrimaryKey
    @NonNull
    @PropertyName("email")
    public String userId;
    @PropertyName("uid")
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
    public User(){}
}
