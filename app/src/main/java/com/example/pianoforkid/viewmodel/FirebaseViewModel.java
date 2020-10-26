package com.example.pianoforkid.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.pianoforkid.data.model.User;
import com.example.pianoforkid.repository.FirebaseRepository;

import java.util.List;

public class FirebaseViewModel  extends AndroidViewModel {
    private LiveData<List<User>> leaderBoard;
    private FirebaseRepository firebaseRepository;


    public FirebaseViewModel(@NonNull Application application) {
        super(application);
        firebaseRepository = FirebaseRepository.getInstance();
        leaderBoard = firebaseRepository.getUserList();
    }

    public void loadLeaderBoard() {
        firebaseRepository.loadLeaderBoard();
    }
    public LiveData<List<User>> getUserList(){
        return firebaseRepository.getUserList();
    }
}
