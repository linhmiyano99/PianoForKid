package com.example.pianoforkid.viewmodel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.pianoforkid.data.model.Song;
import com.example.pianoforkid.data.model.Sound;
import com.example.pianoforkid.repository.SongRepository;

import java.util.List;

public class SongViewModel extends AndroidViewModel {
    private SongRepository songRepository;
    private LiveData<List<Song>> listSongs;
    private LiveData<List<Sound>> currentSong;
    private int currentSongId;
    private LiveData<Integer> lastSongId;
    public SongViewModel(@NonNull Application application) {
        super(application);
        songRepository = SongRepository.getSongRepository(application);
        listSongs = songRepository.getListSongs();
        currentSong = songRepository.getCurrentSong();
        currentSongId = songRepository.getCurrentSongId();
        lastSongId = songRepository.getLastSongId();    }
    public LiveData<List<Song>> getListSongs(){
        return listSongs;
    }
    public LiveData<List<Sound>> getCurrentSong(){
        return currentSong;
    }
    public void loadSongById(int songId){
        if(currentSongId == songId)
            return;
        songRepository.loadSongById(songId);
    }
    public void insertSong(Song song, List<Sound> soundList) {
        songRepository.insertSong(song, soundList);
    }
    public LiveData<Integer> getLastSongId(){
        return lastSongId;
    }
    public int getCurrentSongId(){
        return currentSongId;
    }

}
