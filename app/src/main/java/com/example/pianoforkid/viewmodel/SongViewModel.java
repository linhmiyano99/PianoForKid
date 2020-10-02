package com.example.pianoforkid.viewmodel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.pianoforkid.data.model.LikedSong;
import com.example.pianoforkid.data.model.Song;
import com.example.pianoforkid.data.model.Sound;
import com.example.pianoforkid.repository.LikedSongRepository;
import com.example.pianoforkid.repository.SongRepository;

import java.util.List;

public class SongViewModel extends AndroidViewModel {
    private SongRepository songRepository;
    private LikedSongRepository likedSongRepository;
    private LiveData<List<Song>> listSongs;
    private LiveData<List<LikedSong>> listLikedSongs;
    private LiveData<List<Sound>> currentSong;
    private int currentSongId;
    private LiveData<Integer> lastSongId;
    public SongViewModel(@NonNull Application application) {
        super(application);
        songRepository = SongRepository.getSongRepository(application);
        likedSongRepository = LikedSongRepository.getSongRepository(application);
        listSongs = songRepository.getListSongs();
        listLikedSongs = likedSongRepository.getListSongs();
        currentSong = songRepository.getCurrentSong();
        currentSongId = songRepository.getCurrentSongId();
        lastSongId = songRepository.getLastSongId();    }
    public LiveData<List<Song>> getListSongs(){
        return listSongs;
    }
    public LiveData<List<LikedSong>> getListLikedSongs(){
        return listLikedSongs;
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
    public void insertLikedSong(LikedSong song) {
        likedSongRepository.insertSong(song);
    }
    public void removeLikedSong(int songId) {
        likedSongRepository.removeSong(songId);
    }
    public LiveData<Integer> getLastSongId(){
        return lastSongId;
    }
    public int getCurrentSongId(){
        return currentSongId;
    }

}
