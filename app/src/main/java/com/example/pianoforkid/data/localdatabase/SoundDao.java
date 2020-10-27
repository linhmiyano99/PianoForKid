package com.example.pianoforkid.data.localdatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.pianoforkid.data.model.Sound;

import java.util.List;

@Dao
public interface SoundDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Sound sound);
    @Query("Select * from sound_table where songId = 0 ORDER BY soundId ASC")
    LiveData<List<Sound>> getSong();

    @Query("Select * from sound_table where songId = :currentId ORDER BY soundId ASC")
    List<Sound> getSong(Integer currentId);
}
