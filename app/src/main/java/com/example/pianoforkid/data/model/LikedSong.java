package com.example.pianoforkid.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "liked_song_table")
public class LikedSong {
    @PrimaryKey(autoGenerate = true)
    public int songId;
    public String songName;

    public LikedSong(int songId, String songName) {
        this.songId = songId;
        this.songName = songName;
    }
    @Override
    public String toString() {
        return songId + "- " + songName;
    }
}