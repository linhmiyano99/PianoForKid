package com.example.pianoforkid.repository;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.lifecycle.LiveData;

import com.example.pianoforkid.data.model.Song;
import com.example.pianoforkid.data.model.User;
import com.example.pianoforkid.service.FirebaseService;

import java.util.List;

public class FirebaseRepository {
    private static FirebaseRepository INSTANCE;
    private FirebaseService firebaseService;
    private Application application;

    public static FirebaseRepository getInstance(Application application) {
        if (INSTANCE == null) {
            INSTANCE = new FirebaseRepository(application);
        }
        return INSTANCE;
    }

    private FirebaseRepository(Application application) {
        firebaseService = FirebaseService.getInstance(application);
        this.application = application;
    }

    public void loadLeaderBoard() {
        firebaseService.loadLeaderBoard();
    }

    public LiveData<List<User>> getUserList() {
        return firebaseService.getUserList();
    }

    public void loadAllSongs() {
        firebaseService.loadAllSongs();
    }

    public void loadListLesson() {
        firebaseService.loadListLesson();
    }

    public LiveData<List<Song>> getListSongs() {
        return firebaseService.getListSongs();
    }

    public LiveData<List<String>> getListLessons() {
        return firebaseService.getListLessons();
    }

    public LiveData<List<Song>> getListLessonSongs() {
        return firebaseService.getListLessonSongs();
    }

    public void addScore(int score, User user) {
        if(isNetworkAvailable()) {
            firebaseService.addScore(score, user);
        }
    }

    public void loadListLessonById(String lesson) {
        firebaseService.loadListLessonById(lesson);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)  application.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
