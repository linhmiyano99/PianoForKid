package com.example.pianoforkid.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "sound_table")
public class Sound {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "soundId")
    public int soundId;
    public int songId;
    public int note;
    public long duration;
    public Sound(){
        this.note = 1;
        this.duration = 0;
    }
    public Sound(int songId, int note, long duration){
        this.songId = songId;
        this.note = note;
        this.duration = duration;
    }
    public int getNote() {
        return note;
    }

    public long getDuration() {
        return duration;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
