package com.example.pianoforkid.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pianoforkid.data.localdatabase.LikedDao;
import com.example.pianoforkid.data.localdatabase.SongDao;
import com.example.pianoforkid.data.localdatabase.AppRoomDatabase;
import com.example.pianoforkid.data.localdatabase.SoundDao;
import com.example.pianoforkid.data.model.LikedSong;
import com.example.pianoforkid.data.model.Song;
import com.example.pianoforkid.data.model.Sound;
import com.example.pianoforkid.ultis.ConvertSong;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SongRepository {
    private SongDao songDao;
    private SoundDao soundDao;
    private LikedDao likedDao;
    private LiveData<List<Song>> listSongs;
    private LiveData<List<LikedSong>> listLikedSongs;
    private MutableLiveData<List<Sound>> currentSong;
    private int currentSongId;
    private LiveData<Integer> lastSongId;

    public static SongRepository getSongRepository(Application application){
        if(INSTANCE == null){
            synchronized (SongRepository.class){
                if(INSTANCE == null){
                    INSTANCE = new SongRepository(application);
                }
            }
        }
        return INSTANCE;
    }

    private static SongRepository INSTANCE;
    private SongRepository(Application application){
        AppRoomDatabase db = AppRoomDatabase.getDatabase(application);
        songDao = db.songDao();
        soundDao = db.soundDao();
        likedDao = db.likedDao();
        listSongs = songDao.getListSong();
        listLikedSongs = likedDao.getListLikedSong();
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
        myExecutor.execute(() -> {
            currentSong.postValue(soundDao.getSong(songId));
            currentSongId = songId;
        });
    }

    public void updateCurrentSong(Song song){
        currentSong.setValue(ConvertSong.getConvertStringSongToSound(song.sheet, song.songId));
        currentSongId =song.songId;
    }

    public void insertSong(final Song song, final List<Sound> soundList){
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
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
        });
    }

    public void insertSong(final Song song){
        AppRoomDatabase.databaseWriteExecutor.execute(() -> songDao.insert(song));
    }

    public LiveData<Integer> getLastSongId(){
        return lastSongId;
    }

    public void insertLikedSong(LikedSong song){
        AppRoomDatabase.databaseWriteExecutor.execute(() ->likedDao.insert(song));
    }

    public void deleteFromListLikedSong(int songId){
        AppRoomDatabase.databaseWriteExecutor.execute(() -> likedDao.deleteBySongId(songId));
    }

    public LiveData<List<LikedSong>> getListLikedSongs(){
        return listLikedSongs;
    }

    public LiveData<Song> getSongById(int songId){
        return songDao.getSongById(songId);
    }
}
