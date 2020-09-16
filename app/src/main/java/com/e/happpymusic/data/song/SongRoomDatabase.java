package com.e.happpymusic.data.song;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


import com.e.happpymusic.data.model.Song;
import com.e.happpymusic.data.model.Sound;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Song.class, Sound.class}, version = 1, exportSchema = false)
public abstract class SongRoomDatabase extends RoomDatabase {
    public abstract SongDao songDao();
    public abstract SoundDao soundDao();
    private static SongRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREAD = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREAD);
    public static SongRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (SongRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SongRoomDatabase.class, "song_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
