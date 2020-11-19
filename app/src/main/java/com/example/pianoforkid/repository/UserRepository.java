package com.example.pianoforkid.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pianoforkid.data.localdatabase.AppRoomDatabase;
import com.example.pianoforkid.data.localdatabase.UserDao;
import com.example.pianoforkid.data.model.User;
import com.example.pianoforkid.service.FirebaseService;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;


public class UserRepository {
    private static UserRepository INSTANCE;
    private FirebaseService firebaseService;
    LiveData<User> user;
    UserDao userDao;

    public static UserRepository getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (UserRepository.class){
                if(INSTANCE == null){
                    INSTANCE = new UserRepository(application);
                }
            }
        }
        return INSTANCE;
    }

    private UserRepository(Application application) {
        firebaseService = FirebaseService.getInstance(application);
/*        if(firebaseService.getCurrentUser().getValue() != null) {
            insertUser(firebaseService.getCurrentUser().getValue());
        }*/
        userDao = AppRoomDatabase.getDatabase(application).userDao();
        user = userDao.getUser();
    }

    public void insertUser(FirebaseUser userX) {
        firebaseService.createUser(userX);
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
        User newUser = firebaseService.getUser(userX);
            userDao.insert(newUser);
        });
    }

    public LiveData<User> getUser() {
        return userDao.getUser();
    }

    public void deleteUser(String userId) {
        AppRoomDatabase.databaseWriteExecutor.execute(() -> userDao.deleteById(userId));
    }

    public void addScore(int score) {
        AppRoomDatabase.databaseWriteExecutor.execute(() -> userDao.updateScore(Objects.requireNonNull(user.getValue()).userId, user.getValue().score + score));
    }
}
