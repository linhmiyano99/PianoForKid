package com.example.pianoforkid.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pianoforkid.R;
import com.example.pianoforkid.data.key.KeyNote;
import com.example.pianoforkid.data.model.Song;
import com.example.pianoforkid.data.model.Sound;
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
        song = new ArrayList<>();
        final KeyNote keyNote = new KeyNote(getApplication());
        keyNote.playNote(1);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                keyNote.playNote(2);
            }
        }, 500);

    }
    void reset() {
        song = new ArrayList<>();
    }
    void save(String songName) {
        Song _song = new Song(songId, songName);
        viewModel.insertSong(_song, song);
        startActivity(new Intent(SplashScreenActivity.this, MainMenuActivity.class));
        finish();
    }
    List<Sound> getSongConCoBeBe(){
        List<Sound> soundList = new ArrayList<>();
        Sound sound = new Sound();
        sound.setNote(5);
        sound.setDuration(300);
        soundList.add(sound);
        sound = new Sound();
        sound.setNote(2);
        sound.setDuration(600);
        soundList.add(sound);
        sound = new Sound();
        sound.setNote(7);
        sound.setDuration(300);
        soundList.add(sound);
        sound = new Sound();
        sound.setNote(7);
        sound.setDuration(600);
        soundList.add(sound);
        sound = new Sound();
        sound.setNote(7);
        sound.setDuration(300);
        soundList.add(sound);
        sound = new Sound();
        sound.setNote(3);
        sound.setDuration(600);
        soundList.add(sound);
        sound = new Sound();
        sound.setNote(5);
        sound.setDuration(300);
        soundList.add(sound);
        sound = new Sound();
        sound.setNote(6);
        sound.setDuration(600);
        soundList.add(sound);
        sound = new Sound();
        sound.setNote(6);
        sound.setDuration(300);
        soundList.add(sound);
        sound = new Sound();
        sound.setNote(6);
        sound.setDuration(600);
        soundList.add(sound);
        sound = new Sound();
        sound.setNote(3);
        sound.setDuration(600);
        soundList.add(sound);
        sound = new Sound();
        sound.setNote(3);
        sound.setDuration(600);
        soundList.add(sound);
        sound = new Sound();
        sound.setNote(9);
        sound.setDuration(600);
        soundList.add(sound);
        sound = new Sound();
        sound.setNote(7);
        sound.setDuration(600);
        soundList.add(sound);
        sound = new Sound();
        sound.setNote(6);
        sound.setDuration(600);
        soundList.add(sound);
        sound = new Sound();
        sound.setNote(6);
        sound.setDuration(600);
        soundList.add(sound);
        sound = new Sound();
        sound.setNote(7);
        sound.setDuration(300);
        soundList.add(sound);
        sound = new Sound();
        sound.setNote(7);
        sound.setDuration(600);
        soundList.add(sound);
        sound = new Sound();
        sound.setNote(7);
        sound.setDuration(600);
        soundList.add(sound);
        sound = new Sound();
        sound.setNote(3);
        sound.setDuration(600);
        soundList.add(sound);
        sound = new Sound();
        sound.setNote(7);
        sound.setDuration(300);
        soundList.add(sound);
        sound = new Sound();
        sound.setNote(6);
        sound.setDuration(600);
        soundList.add(sound);
        sound = new Sound();
        sound.setNote(7);
        sound.setDuration(600);
        soundList.add(sound);
        sound = new Sound();
        sound.setNote(6);
        sound.setDuration(600);
        soundList.add(sound);
        sound = new Sound();
        sound.setNote(2);
        sound.setDuration(300);
        soundList.add(sound);
        sound = new Sound();
        sound.setNote(5);
        sound.setDuration(600);
        soundList.add(sound);
        sound = new Sound();
        sound.setNote(6);
        sound.setDuration(600);
        soundList.add(sound);
        sound = new Sound();
        sound.setNote(7);
        sound.setDuration(600);
        soundList.add(sound);
        sound = new Sound();
        sound.setNote(2);
        sound.setDuration(600);
        soundList.add(sound);
        sound = new Sound();
        sound.setNote(7);
        sound.setDuration(300);
        soundList.add(sound);
        sound = new Sound();
        sound.setNote(6);
        sound.setDuration(300);
        soundList.add(sound);
        sound = new Sound();
        sound.setNote(5);
        sound.setDuration(300);
        soundList.add(sound);
        sound = new Sound();
        sound.setNote(6);
        sound.setDuration(300);
        soundList.add(sound);
        sound = new Sound();

        sound.setNote(5);
        sound.setDuration(600);
        soundList.add(sound);
        sound = new Sound();

        sound.setNote(5);
        sound.setDuration(300);
        soundList.add(sound);
        sound = new Sound();

        sound.setNote(5);
        sound.setDuration(600);
        soundList.add(sound);
        return soundList;
    }
}