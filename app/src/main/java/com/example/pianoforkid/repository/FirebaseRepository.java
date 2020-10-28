package com.example.pianoforkid.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pianoforkid.data.model.Song;
import com.example.pianoforkid.data.model.User;
import com.example.pianoforkid.service.FirebaseService;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class FirebaseRepository {
    private static FirebaseRepository INSTANCE ;
    private LiveData<List<User>> leaderBoard;
    private FirebaseService firebaseService;
    public static FirebaseRepository getInstance(Application application) {
        if (INSTANCE == null) {
            INSTANCE = new FirebaseRepository(application);
        }
        return INSTANCE;
    }

    private FirebaseRepository(Application application) {
        leaderBoard = new MutableLiveData<>();
        firebaseService = FirebaseService.getInstance(application);
    }

    public void loadLeaderBoard() {
        firebaseService.loadLeaderBoard();
    }
    public LiveData<List<User>> getUserList(){
        return firebaseService.getUserList();
    }

    public void loadAllSongs(){
        firebaseService.loadAllSongs();
    }
    public LiveData<List<Song>> getListSongs() {
        return firebaseService.getListSongs();
    }

    public void createUser(FirebaseUser user){
        firebaseService.createUser(user);
    }
    public void addScore(int score, User user){
        firebaseService.addScore(score, user);
    }
}
