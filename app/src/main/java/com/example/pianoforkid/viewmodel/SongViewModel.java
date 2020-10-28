package com.example.pianoforkid.viewmodel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.pianoforkid.data.model.LikedSong;
import com.example.pianoforkid.data.model.Song;
import com.example.pianoforkid.data.model.Sound;
import com.example.pianoforkid.repository.SongRepository;

import java.util.List;

public class SongViewModel extends AndroidViewModel {
    private SongRepository songRepository;
    private LiveData<List<Song>> listSongs;
    private LiveData<List<Sound>> currentSong;
    private LiveData<List<LikedSong>> listLikedSongs;
    private int currentSongId;
    private LiveData<Integer> lastSongId;

    public SongViewModel(@NonNull Application application) {
        super(application);
        songRepository = SongRepository.getSongRepository(application);
        listSongs = songRepository.getListSongs();
        currentSong = songRepository.getCurrentSong();
        currentSongId = songRepository.getCurrentSongId();
        lastSongId = songRepository.getLastSongId();
        listLikedSongs = songRepository.getListLikedSongs();
    }

    public LiveData<List<Song>> getListSongs(){
        return listSongs;
    }

    public LiveData<List<Sound>> getCurrentSong(){
        return currentSong;
    }

    public LiveData<Song> getSongById(int songId){
        return songRepository.getSongById(songId);
    }

    public void loadSongById(int songId){
        if(currentSongId == songId)
            return;
        songRepository.loadSongById(songId);
    }

    public void updateCurrentSong(Song song){
        songRepository.updateCurrentSong(song);
    }

    public void insertSong(Song song, List<Sound> soundList) {
        songRepository.insertSong(song, soundList);
    }

    public void insertSong(Song song) {
        songRepository.insertSong(song);
    }

    public LiveData<Integer> getLastSongId(){
        return lastSongId;
    }

    public int getCurrentSongId(){
        return currentSongId;
    }

    public LiveData<List<LikedSong>> getListLikedSongs(){
        return songRepository.getListLikedSongs();
    }

    public void insertLikedSong(LikedSong song){
        songRepository.insertLikedSong(song);
    }

    public void deleteFromListLikedSong(int songId){
        songRepository.deleteFromListLikedSong(songId);
    }
}
