package com.example.pianoforkid.data.like;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.pianoforkid.data.model.LikedSong;
import com.example.pianoforkid.data.model.Song;

import java.util.List;

@Dao
public interface LikedSongDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(LikedSong song);
    @Query("Select * from liked_song_table ORDER BY songId ASC")
    LiveData<List<LikedSong>> getListLikedSong();
    @Query("DELETE FROM liked_song_table where songId = :songId")
    void remove(int songId);
}
