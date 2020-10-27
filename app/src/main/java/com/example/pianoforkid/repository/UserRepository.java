package com.example.pianoforkid.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.pianoforkid.data.localdatabase.AppRoomDatabase;
import com.example.pianoforkid.data.localdatabase.UserDao;
import com.example.pianoforkid.data.model.User;


public class UserRepository {
    private static UserRepository INSTANCE ;
    LiveData<User> user;
    UserDao userDao;
    public static UserRepository getInstance(Application application) {
        if (INSTANCE == null) {
            INSTANCE = new UserRepository(application);
        }
        return INSTANCE;
    }

    private UserRepository(Application application) {
        userDao = AppRoomDatabase.getDatabase(application).userDao();
        user = userDao.getUser();
    }
    public void insertUser(User userX){
        AppRoomDatabase.databaseWriteExecutor.execute(() -> userDao.insert(userX));
    }
    public LiveData<User> getUser(){
        return user;
    }

}
