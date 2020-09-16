package com.example.pianoforkid.data.song;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.pianoforkid.data.model.Song;

import java.util.List;

@Dao
public interface SongDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Song song);
    @Query("Select * from song_table ORDER BY songId ASC")
    LiveData<List<Song>> getListSong();
    @Query("Select COUNT(*) from song_table")
    LiveData<Integer> getLastId();
}
