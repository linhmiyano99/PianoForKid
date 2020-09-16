package com.e.happpymusic.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.e.happpymusic.data.song.SongDao;
import com.e.happpymusic.data.song.SongRoomDatabase;
import com.e.happpymusic.data.song.SoundDao;
import com.e.happpymusic.data.model.Song;
import com.e.happpymusic.data.model.Sound;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SongRepository {
    private SongDao songDao;
    private SoundDao soundDao;
    private LiveData<List<Song>> listSongs;
    private MutableLiveData<List<Sound>> currentSong;
    private int currentSongId;
    private LiveData<Integer> lastSongId;

    public static SongRepository getSongRepository(Application application){
        if(INSTANCE == null){
            INSTANCE = new SongRepository(application);
        }
        return INSTANCE;
    }
    private static SongRepository INSTANCE;
    private SongRepository(Application application){
        SongRoomDatabase db = SongRoomDatabase.getDatabase(application);
        songDao = db.songDao();
        soundDao = db.soundDao();
        listSongs = songDao.getListSong();
        currentSong = new MutableLiveData<>();
        currentSongId = 0;
        lastSongId = songDao.getLastId();
    }

    public LiveData<List<Song>> getListSongs() {
        return listSongs;
    }

    public LiveData<List<Sound>> getCurrentSong() {
        return currentSong;
    }
    public int getCurrentSongId() {
        return currentSongId;
    }
    public void loadSongById(final int songId) {
        Executor myExecutor = Executors.newSingleThreadExecutor();
        myExecutor.execute(new Runnable() {
            @Override
            public void run() {
                currentSong.postValue(soundDao.getSong(songId));
                currentSongId = songId;
            }
        });
    }

    public void insertSong(final Song song, final List<Sound> soundList){
        SongRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                long duration = 0;
                for (Sound sound : soundList
                ) {
                    duration += sound.duration;
                }
                song.songDuration = duration;
                songDao.insert(song);
                for (Sound sound : soundList
                ) {
                    sound.songId = song.songId;
                    soundDao.insert(sound);
                }
            }
        });
    }
    public LiveData<Integer> getLastSongId(){
        return lastSongId;
    }
}
