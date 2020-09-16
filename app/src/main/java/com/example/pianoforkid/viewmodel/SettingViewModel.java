package com.example.pianoforkid.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.pianoforkid.repository.SettingRepository;

public class SettingViewModel extends AndroidViewModel {
    private SettingRepository settingRepository;
    private LiveData<Boolean> language;
    private LiveData<Boolean> isSound;
    private LiveData<Boolean> isBackgroundMusic;

    public SettingViewModel(Application application){
        super(application);
        settingRepository = SettingRepository.getSettingRepository(application);
        language = settingRepository.getLanguage();
        isSound = settingRepository.getIsSound();
        isBackgroundMusic = settingRepository.getIsBackgroundMusic();
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

    public void updateSetting(String key) {
        settingRepository.updateSetting(key);
    }
}
