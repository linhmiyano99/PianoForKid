package com.example.pianoforkid.data.model;

import androidx.room.Entity;

@Entity(tableName = "song_table")
public class Lesson {
    public int lessonId;
    public String lessonName;

    public Lesson(int lessonId, String lessonName) {
        this.lessonId = lessonId;
        this.lessonName = lessonName;
    }
    @Override
    public String toString() {
        return lessonId + "- " + lessonName;
    }
}
