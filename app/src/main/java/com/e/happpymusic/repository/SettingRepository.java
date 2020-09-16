package com.e.happpymusic.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.e.happpymusic.data.setting.SettingDatabase;


public class SettingRepository {
    private static SettingRepository INSTANCE;
    private SettingDatabase settingDatabase;
    private LiveData<Boolean> language;
    private LiveData<Boolean> isSound;
    private LiveData<Boolean> isBackgroundMusic;

    public static SettingRepository getSettingRepository(Application application) {
        if(INSTANCE == null){
            INSTANCE = new SettingRepository(application);
        }
        return INSTANCE;
    }

    private SettingRepository(Application application){
        settingDatabase = SettingDatabase.getSettingRepository(application);
        language = settingDatabase.getLanguage();
        isSound = settingDatabase.getIsSound();
        isBackgroundMusic = settingDatabase.getIsBackgroundMusic();
    }

    public void updateSetting(String key) {
        settingDatabase.updateSetting(key);
    }

    public LiveData<Boolean> getLanguage() {
        return language;
    }

    public LiveData<Boolean> getIsSound() {
        return isSound;
    }

    public LiveData<Boolean> getIsBackgroundMusic() {
        return isBackgroundMusic;
    }
}
