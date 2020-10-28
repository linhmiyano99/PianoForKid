package com.example.pianoforkid.data.localdatabase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.pianoforkid.data.model.User;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(User user);
    @Query("Delete from user_table where userId = :userId")
    void deleteById(String userId);
    @Query("Select * from user_table order by userId asc")
    LiveData<User> getUser();
    @Query("Update user_table set  score = :score where userId = :id")
    void updateScore(String id, int score);
}
