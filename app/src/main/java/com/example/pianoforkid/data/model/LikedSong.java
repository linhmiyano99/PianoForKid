package com.example.pianoforkid.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "liked_song_table")
public class LikedSong {
    @PrimaryKey
    @NonNull
    public int songId;
    public String songName;
    public int remoteId;
}
