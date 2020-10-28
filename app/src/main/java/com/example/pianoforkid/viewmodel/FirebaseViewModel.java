package com.example.pianoforkid.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.pianoforkid.data.model.Song;
import com.example.pianoforkid.data.model.User;
import com.example.pianoforkid.repository.FirebaseRepository;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class FirebaseViewModel  extends AndroidViewModel {
    private LiveData<List<User>> leaderBoard;
    private FirebaseRepository firebaseRepository;

    public FirebaseViewModel(@NonNull Application application) {
        super(application);
        firebaseRepository = FirebaseRepository.getInstance(application);
        leaderBoard = firebaseRepository.getUserList();
    }

    public void loadLeaderBoard() {
        firebaseRepository.loadLeaderBoard();
    }

    public LiveData<List<User>> getUserList(){
        return firebaseRepository.getUserList();
    }

    public void loadAllSongs(){
        firebaseRepository.loadAllSongs();
    }

    public LiveData<List<Song>> getListSongs() {
        return firebaseRepository.getListSongs();
    }

    public void createUser(FirebaseUser user){
        firebaseRepository.createUser(user);
    }

    public void addScore(int score, User user){
        firebaseRepository.addScore(score, user);
    }
}
