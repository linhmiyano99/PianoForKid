package com.example.pianoforkid.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.pianoforkid.data.localdatabase.AppRoomDatabase;
import com.example.pianoforkid.data.localdatabase.UserDao;
import com.example.pianoforkid.data.model.User;
import com.example.pianoforkid.service.FirebaseService;


public class UserRepository {
    private static UserRepository INSTANCE ;
    private FirebaseService firebaseService;
    LiveData<User> user;
    UserDao userDao;
    public static UserRepository getInstance(Application application) {
        if (INSTANCE == null) {
            INSTANCE = new UserRepository(application);
        }
        return INSTANCE;
    }

    private UserRepository(Application application) {
        firebaseService= FirebaseService.getInstance(application);
/*        if(firebaseService.getCurrentUser().getValue() != null) {
            insertUser(firebaseService.getCurrentUser().getValue());
        }*/
        userDao = AppRoomDatabase.getDatabase(application).userDao();
        user = userDao.getUser();
    }
    public void insertUser(User userX){
        AppRoomDatabase.databaseWriteExecutor.execute(() -> userDao.insert(userX));
    }
    public LiveData<User> getUser(){
        return userDao.getUser();
    }
    public void deleteUser(String userId){
        AppRoomDatabase.databaseWriteExecutor.execute(() -> userDao.deleteById(userId));
    }
}
