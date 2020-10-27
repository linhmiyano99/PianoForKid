package com.example.pianoforkid.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pianoforkid.data.model.User;
import com.example.pianoforkid.service.FirebaseService;

import java.util.List;

public class FirebaseRepository {
    private static FirebaseRepository INSTANCE ;
    private LiveData<List<User>> leaderBoard;
    private FirebaseService firebaseService;
    public static FirebaseRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FirebaseRepository();
        }
        return INSTANCE;
    }

    private FirebaseRepository() {
        leaderBoard = new MutableLiveData<>();
        firebaseService = FirebaseService.getInstance();
    }

    public void loadLeaderBoard() {
        firebaseService.loadLeaderBoard();
    }
    public LiveData<List<User>> getUserList(){
        return firebaseService.getUserList();
    }
}
