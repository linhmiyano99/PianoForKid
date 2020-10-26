package com.example.pianoforkid.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "song_table")
public class Song {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int songId;
    public String songName;
    public String songAuthor;
    public long songDuration;
    public String sheet;
	public Song(int songId, String songName) {
		this.songId = songId;
		this.songName = songName;
		songAuthor = "you";
	}

    @Override
    public String toString() {
        return songId + "- " + songName;
    }
}
