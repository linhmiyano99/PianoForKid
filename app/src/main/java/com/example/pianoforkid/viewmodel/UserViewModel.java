package com.example.pianoforkid.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.pianoforkid.data.model.User;
import com.example.pianoforkid.repository.UserRepository;
import com.google.firebase.auth.FirebaseUser;


public class UserViewModel extends AndroidViewModel {
    private LiveData<User> user;
    private UserRepository userRepository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = UserRepository.getInstance(application);
        user = userRepository.getUser();
    }

    public void insertUser(FirebaseUser userX){
        userRepository.insertUser(userX);
    }

    public LiveData<User> getUser(){
        return userRepository.getUser();
    }

    public void delete(String useId){
        userRepository.deleteUser(useId);
    }

    public void addScore(int score){
        userRepository.addScore(score);
    }
}
