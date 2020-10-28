package com.example.pianoforkid.data.localdatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


import com.example.pianoforkid.data.model.Song;
import com.example.pianoforkid.data.model.Sound;
import com.example.pianoforkid.data.model.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Song.class, Sound.class, User.class}, version = 2, exportSchema = false)
public abstract class AppRoomDatabase extends RoomDatabase {
    public abstract SongDao songDao();
    public abstract SoundDao soundDao();
    public abstract UserDao userDao();
    private static AppRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREAD = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREAD);
    public static AppRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (AppRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppRoomDatabase.class, "app_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
