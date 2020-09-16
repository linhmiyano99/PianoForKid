package com.example.pianoforkid.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.pianoforkid.R;
import com.example.pianoforkid.viewmodel.SettingViewModel;
import com.example.pianoforkid.viewmodel.SongViewModel;

public class MainMenuActivity extends AppCompatActivity implements View.OnClickListener {

    SongViewModel viewModel;
    SettingViewModel settingViewModel;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, MainMenuActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Log.d("MainMenuActivity", "onCreate");


        Button button_songs = findViewById(R.id.buttonListSong);
        Button button_instruments = findViewById(R.id.buttonInstrument);
        ImageButton button_user = findViewById(R.id.imageButtonUser);
        ImageView button_settings = findViewById(R.id.imageButtonSetting);
        button_songs.setOnClickListener(this);
        button_instruments.setOnClickListener(this);
        button_user.setOnClickListener(this);
        button_settings.setOnClickListener(this);
        viewModel = new ViewModelProvider(this).get(SongViewModel.class);
        settingViewModel = new ViewModelProvider(this).get(SettingViewModel.class);
    }
    @Override
    public void onClick(View v) {
        // Perform action on click

        switch (v.getId()) {
            case R.id.buttonListSong:
                viewModel.getListSongs().observe(this, songs ->
                {
                });
                SongListActivity.startActivity(this);
                break;
            case R.id.buttonInstrument:
                SaveMusicActivity.startActivity(this);
                break;
        }
    }

}
