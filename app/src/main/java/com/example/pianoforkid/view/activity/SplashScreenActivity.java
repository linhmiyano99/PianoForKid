package com.example.pianoforkid.view.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.pianoforkid.R;
import com.example.pianoforkid.data.model.Song;
import com.example.pianoforkid.data.model.Sound;
import com.example.pianoforkid.ultis.ConvertSong;
import com.example.pianoforkid.viewmodel.SongViewModel;

import java.util.ArrayList;
import java.util.List;

public class SplashScreenActivity extends AppCompatActivity {
    List<Sound> song;
    SongViewModel viewModel;
    int songId = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Log.d("SplashScreenActivity", "onCreate");

        viewModel = new ViewModelProvider(this).get(SongViewModel.class);
        viewModel.getLastSongId().observe(this, integer -> {
            if(integer>0) {
                MainMenuActivity.startActivity(this);
                finish();
            }
            else{
                song = new ArrayList<>();
                song.addAll(getSongConCoBeBe());
                save();
            }
        });
    }
    void save() {
        Song _song = new Song(songId, "Con cò bé bé");
        viewModel.insertSong(_song, song);
        songId++;
        MainMenuActivity.startActivity(this);
        finish();
    }
    List<Sound> getSongConCoBeBe(){
        return ConvertSong.getConvertStringSongToSound("(1,2)",1);
    }
}