package com.example.pianoforkid.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pianoforkid.data.like.LikedSongDao;
import com.example.pianoforkid.data.like.LikedSongRoomDatabase;
import com.example.pianoforkid.data.model.LikedSong;
import com.example.pianoforkid.data.model.Song;
import com.example.pianoforkid.data.model.Sound;
import com.example.pianoforkid.data.song.SongRoomDatabase;

import java.util.List;

public class LikedSongRepository {
    private LikedSongDao songDao;
    private LiveData<List<LikedSong>> listSongs;

    public static LikedSongRepository getSongRepository(Application application){
        if(INSTANCE == null){
            INSTANCE = new LikedSongRepository(application);
        }
        return INSTANCE;
    }
    private static LikedSongRepository INSTANCE;
    private LikedSongRepository(Application application){
        LikedSongRoomDatabase db = LikedSongRoomDatabase.getDatabase(application);
        songDao = db.songDao();
        listSongs = songDao.getListLikedSong();
    }

    public LiveData<List<LikedSong>> getListSongs() {
        return listSongs;
    }

    public void insertSong(final LikedSong song){
        SongRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                songDao.insert(song);

            }
        });
    }
    public void removeSong(int songId){
        SongRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                songDao.remove(songId);

            }
        });
    }
}
