package com.example.pianoforkid.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.pianoforkid.data.model.Song;
import com.example.pianoforkid.data.model.User;
import com.example.pianoforkid.service.FirebaseService;

import java.util.List;

public class FirebaseRepository {
    private static FirebaseRepository INSTANCE;
    private FirebaseService firebaseService;

    public static FirebaseRepository getInstance(Application application) {
        if (INSTANCE == null) {
            INSTANCE = new FirebaseRepository(application);
        }
        return INSTANCE;
    }

    private FirebaseRepository(Application application) {
        firebaseService = FirebaseService.getInstance(application);
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
        firebaseService.addScore(score, user);
    }

    public void loadListLessonById(String lesson) {
        firebaseService.loadListLessonById(lesson);
    }
}
