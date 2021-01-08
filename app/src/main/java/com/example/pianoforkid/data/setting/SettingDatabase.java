package com.example.pianoforkid.data.setting;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pianoforkid.R;
import com.example.pianoforkid.view.activity.SettingActivity;

import java.util.Map;

public class SettingDatabase implements ListSettingResponse {
	private static SettingDatabase INSTANCE;
	private MutableLiveData<Boolean> language;
	private MutableLiveData<Boolean> isSound;
	private MutableLiveData<Boolean> isBackgroundMusic;
	private Context context;
	
	private SettingDatabase(Application application) {
		this.context = application.getApplicationContext();
		language = new MutableLiveData<>();
		isSound = new MutableLiveData<>();
		isBackgroundMusic = new MutableLiveData<>();
		loadSettingData();
		
	}
	
	public static SettingDatabase getSettingRepository(Application application) {
		if (INSTANCE == null) {
			INSTANCE = new SettingDatabase(application);
		}
		return INSTANCE;
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
	
	@Override
	public void processListSettingFinish(Map<String, Boolean> output) {
		language.setValue(output.get("language"));
		isSound.setValue(output.get("isSound"));
		isBackgroundMusic.setValue(output.get("isBackgroundMusic"));
	}
	
	private void loadSettingData() {
		LoadSettingTask task = new LoadSettingTask();
		//this to set delegate / listener back to this class
		task.setDelegate(this);
		task.execute(context);
	}
	
	public void updateSetting(String key) {
		MediaPlayer mediaPlayer=MediaPlayer.create(context.getApplicationContext(),R.raw.bensound_ukulele);
		mediaPlayer.setLooping(false);
		boolean value = false;
		switch (key) {
			case "language":
				if (!language.getValue()) {
					value = true;
					language.setValue(true);
				} else {
					language.setValue(false);
				}
				break;
			case "isSound":
				if (!isSound.getValue()) {
					value = true;
					isSound.setValue(true);

				} else {
					isSound.setValue(false);

				}
				break;
			case "isBackgroundMusic":
				if (!isBackgroundMusic.getValue()) {
					value = true;
					isBackgroundMusic.setValue(true);
					mediaPlayer.start();
				} else {
					isBackgroundMusic.setValue(false);
					mediaPlayer.stop();
				}
				break;
		}
		
		UpdateSetting task = new UpdateSetting(key, value);
		task.execute(context);


	}
}
