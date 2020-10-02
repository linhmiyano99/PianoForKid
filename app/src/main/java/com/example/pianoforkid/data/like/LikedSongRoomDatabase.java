package com.example.pianoforkid.data.like;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.pianoforkid.data.model.LikedSong;
import com.example.pianoforkid.data.song.SongRoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {LikedSong.class}, version = 1, exportSchema = false)
public abstract class LikedSongRoomDatabase extends RoomDatabase {
    public abstract LikedSongDao songDao();
    private static LikedSongRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREAD = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREAD);
    public static LikedSongRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (SongRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            LikedSongRoomDatabase.class, "liked_song_table")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
