package com.example.pianoforkid.data.localdatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.pianoforkid.data.model.LikedSong;

import java.util.List;

@Dao
public interface LikedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(LikedSong song);

    @Query("DELETE FROM liked_song_table WHERE songId = :songId")
    void deleteBySongId(int songId);

    @Query("Select * from liked_song_table ORDER BY songId ASC")
    LiveData<List<LikedSong>> getListLikedSong();
}
