package com.example.pianoforkid.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "lesion_table")
public class Lesson {
    @PrimaryKey
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
