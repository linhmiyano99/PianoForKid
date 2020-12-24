package com.example.pianoforkid.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;

import com.example.pianoforkid.R;
import com.example.pianoforkid.service.MyService;
import com.example.pianoforkid.viewmodel.SettingViewModel;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener{

    private SettingViewModel settingViewModel;


    public static void startActivity(Context context){
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Log.d("SettingActivity", "onCreate");

        settingViewModel = new ViewModelProvider(this).get(SettingViewModel.class);

        final ImageButton btnSound = findViewById(R.id.button_sound);
        final ImageButton  btnBackgroundMusic = findViewById(R.id.button_music);
        final CardView cvLanguage= findViewById(R.id.card_view_language);
        ImageButton button_back = findViewById(R.id.button_back);
        button_back.setOnClickListener(v -> onBackPressed());

        btnSound.setOnClickListener(v -> settingViewModel.updateSetting("isSound"));
        btnBackgroundMusic.setOnClickListener(v -> settingViewModel.updateSetting("isBackgroundMusic"));
        cvLanguage.setOnClickListener(v -> settingViewModel.updateSetting("language"));

        settingViewModel.getIsSound().observe(this, value -> {
            Log.d("getIsBackgroundMusic", String.valueOf(value));
            if(value) {
                btnSound.setImageResource(R.drawable.sound_on);
            }
            else
                btnSound.setImageResource(R.drawable.sound_off);
        });
        settingViewModel.getIsBackgroundMusic().observe(this, value -> {
            Log.d("getIsBackgroundMusic", String.valueOf(value));
            if(value) {
                btnBackgroundMusic.setImageResource(R.drawable.background_music_on);

            }else {
                btnBackgroundMusic.setImageResource(R.drawable.background_music_off);
            }
        });

        btnBackgroundMusic.setOnClickListener(this);
        btnSound.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card_view_language:
                settingViewModel.updateSetting("language");
                break;
            case R.id.button_sound:
                settingViewModel.updateSetting("isSound");
                break;
            case R.id.button_music:
                settingViewModel.updateSetting("isBackgroundMusic");
                break;
        }
    }
}
