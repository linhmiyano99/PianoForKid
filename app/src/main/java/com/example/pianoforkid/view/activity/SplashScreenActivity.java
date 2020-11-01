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

    // Songs of lesson 1
    List<Sound> getSongConCoBeBe(){
        return ConvertSong.getConvertStringSongToSound("(1,200)(2,300)(5,300)",1);
    }
    /*
    List<Sound> getSongPractice1Lesson1(){
        return ConvertSong.getConvertStringSongToSound("(1,600)(2,600)(3,600)(4,600)(5,600)(1,600)(2,600)(3,600)(4,600)(5,600)(5,600)(4,600)(3,600)(2,600)(1,600)(5,600)(4,600)(3,600)(2,600)(1,600)(1,600)(2,600)(3,600)(4,600)(5,600)(5,600)(4,600)(3,600)(2,600)(1,600)",2);
    }
    List<Sound> getSongPractice2Lesson1(){
        return ConvertSong.getConvertStringSongToSound("(1,600)(2,600)(1,600)(2,600)(3,600)(2,600)(3,600)(2,600)(3,600)(4,600)(3,600)(4,600)(5,600)(4,600)(5,600)(4,600)(5,600)(4,600)(3,600)(4,600)(5,600)(4,600)(3,600)(2,600)(1,600)(2,600)(3,600)(2,600)(1,2400)",3);
    }
    List<Sound> getSongPractice3Lesson1(){
        return ConvertSong.getConvertStringSongToSound("(3,600)(4,600)(5,600)(4,600)(3,600)(4,600)(5,600)(4,600)(3,600)(2,600)(1,600)(2,600)(3,600)(2,600)(1,600)(2,600)(3,600)(4,600)(5,600)(4,600)(3,600)(2,600)(3,600)(4,600)(5,600)(4,600)(3,600)(2,600)(1,2400)",4);
    }
    List<Sound> getSongPractice4Lesson1(){
        return ConvertSong.getConvertStringSongToSound("(5,600)(4,600)(5,600)(4,600)(5,600)(4,600)(3,600)(2,600)(1,600)(2,600)(1,600)(2,600)(1,600)(2,600)(3,600)(4,600)(5,600)(4,600)(5,600)(4,600)(5,600)(4,600)(3,600)(2,600)(3,600)(2,600)(1,2400)",5);
    }
    List<Sound> getSongPractice5Lesson1(){
        return ConvertSong.getConvertStringSongToSound("(1,600)(2,600)(3,600)(2,600)(1,600)(3,600)(1,600)(3,600)(2,600)(3,600)(4,600)(3,600)(2,600)(4,600)(2,600)(4,600)(3,600)(4,600)(5,600)(4,600)(3,600)(5,600)(3,600)(5,600)(4,600)(3,600)(2,600)(3,600)(1,2400)",6);
    }
    List<Sound> getSongMerrilyWeRollAlong(){
        return ConvertSong.getConvertStringSongToSound("(4,600)(3,600)(2,600)(3,600)(4,600)(4,600)(4,1200)(3,600)(3,600) (3,1200)(4,600)(6,600)(6,1200)(4,600)(3,600)(2,600)(3,600)(4,600)(4,600)(4,1200)(3,600)(3,600)(4,600)(3,600)(2,600)",7);
    }
    List<Sound> getSongOdeToJoy() {
        return ConvertSong.getConvertStringSongToSound("(3,600)(4,600)(5,600)(5,600)(4,600)(3,600)(2,600)(1,600)(1,600)(2,600)(3,600)(3,1200)(2,1200)(3,1200)(4,600)(5,600)(5,600)(4,600)(3,600)(2,600)(1,600)(1,600)(2,600)(3,600)(2,1200)(1,1200)", 8);
    }
    // Songs of lesson 1
    List<Sound> getSongPractice1Lesson2() {
        return ConvertSong.getConvertStringSongToSound("(1,600)(2,600)(3,600)(4,600)(5,600)(1,600)(2,600)(3,600)(4,600)(5,600)(5,600)(4,600)(3,600)(2,600)(1,600)(5,600)(4,600)(3,600)(2,600)(1,600)(1,600)(2,600)(3,600)(4,600)(5,600)(5,600)(4,600)(3,600)(2,600)(1,600)", 9);
    }
    List<Sound> getSongPractice2Lesson2(){
        return ConvertSong.getConvertStringSongToSound("(1,600)(3,600)(1,600)(3,600)(2,600)(4,600)(2,600)(4,600)(3,600)(5,600)(3,600)(5,600)(4,600)(2,600)(4,600)(2,600)(1,600)(3,600)(5,600)(3,600)(1,600)(2,600)(3,600)(4,600)(5,600)(3,600)(2,600)(3,600)(1,2400)",10);
    }
    List<Sound> getSongPractice3Lesson2(){
        return ConvertSong.getConvertStringSongToSound("(1,600)(2,600)(3,600)(4,600)(1,600)(4,600)(1,600)(4,600)(1,600)(2,600)(3,600)(4,600)(5,600)(2,600)(5,600)(2,600)(5,600)(4,600)(3,600)(2,600)(1,600)(4,600)(1,600)(4,600)(5,600)(4,600)(3,600)(2,600)(1,2400)",11);
    }
    List<Sound> getSongPractice4Lesson2(){
        return ConvertSong.getConvertStringSongToSound("(1,600)(2,600)(3,600)(4,600)(5,600)(1,600)(5,600)(1,600)(5,600)(4,600)(3,600)(2,600)(1,600)(5,600)(1,600)(5,600)(3,600)(2,600)(1,600)(3,600)(5,600)(1,600)(5,600)(1,600)(2,600)(3,600)(2,600)(3,600)(1,2400)",12);
    }
    List<Sound> getSongPractice5Lesson2() {
        return ConvertSong.getConvertStringSongToSound("(1,600)(2,600)(1,600)(2,600)(1,600)(3,600)(1,600)(3,600)(1,600)(4,600)(1,600)(4,600)(1,600)(5,600)(1,600)(5,600)(4,600)(5,600)(4,600)(5,600)(3,600)(5,600)(3,600)(5,600)(2,600)(5,600)(2,600)(5,600)(1,600)(5,600)(1,1200)", 13);
    }
    List<Sound> getSongPatterns() {
        return ConvertSong.getConvertStringSongToSound("(1,600)(2,600)(3,600)(1,600)(2,600)(3,600)(4,600)(2,600)(3,600)(4,600)(5,600)(3,600)(2,2400)(1,600)(2,600)(3,600)(1,600)(2,600)(3,600)(4,600)(2,600)(3,600)(4,600)(5,600)(3,600)(1,2400)", 14);
    }
    List<Sound> getSongTheTrafficCop() {
        return ConvertSong.getConvertStringSongToSound("(3,600)(3,600)(1,600)(3,600)(3,600)(1,600)(2,600)(2,600)(3,600)(2,600)(1,600)(3,600)(5,1200)(3,600)(3,600)(1,2400)(3,600)(3,600)(1,2400)(2,600)(2,600)(3,600)(2,600)(1,600)(3,600)(1,1200)", 15);
    }
    // Song of lesson 5
    List<Sound> getSongL() {
        return ConvertSong.getConvertStringSongToSound("(3,600)(1,600)(3,600)(1,600)(3,600)(1,600)(2,2400)(2,600)(3,600)(4,600)(2,600)(3,600)(2,600)(1,1200)(3,600)(1,600)(3,600)(1,600)(3,600)(1,600)(2,1200)(2,600)(3,600)(4,600)(2,600)(3,600)(2,600)(1,1200)", 16);
    }
    List<Sound> getSongDoReMi() {
        return ConvertSong.getConvertStringSongToSound("(1,600)(2,600)(3,1200)(3,600)(3,600)(3,1200)(3,600)(4,600)(5,1200)(5,600)(5,600)(5,1200)(4,600)(3,600)(2,1200)(2,600)(2,600)(2,1200)(3,600)(2,600)(1,1200)(1,600)(1,600)(1,1200)", 17);
    }
    List<Sound> getSongTheChicks() {
        return ConvertSong.getConvertStringSongToSound("(4,600)(4,600)(1,600)(1,600)(2,600)(2,600)(1,1200)(4,600)(4,600)(1,600)(1,600)(2,600)(2,600)(1,1200)(1,600)(1,600)(2,600)(3,600)(4,1200)(4,1200)(1,600)(1,600)(2,600)(3,600)(4,1200)(4,1200)", 18);
    }
    List<Sound> getSongTwinkleTwinkleLittleStar() {
        return ConvertSong.getConvertStringSongToSound("(1,600)(1,600)(5,600)(5,600)(6,600)(6,600)(5,1200)(4,600)(4,600)(3,600)(3,600)(2,600)(2,600)(1,1200)(5,600)(5,600)(4,600)(4,600)(3,600)(3,600)(2,1200)(5,600)(5,600)(4,600)(4,600)(3,600)(3,600)(2,1200)(1,600)(1,600)(5,600)(5,600)(6,600)(6,600)(5,1200)(4,600)(4,600)(3,600)(3,600)(2,600)(2,600)(1,1200)", 19);
    }
    List<Sound> getSongLightlyRow() {
        return ConvertSong.getConvertStringSongToSound("(5,600)(3,600)(3,1200)(4,600)(2,600)(2,1200)(1,600)(2,600)(3,600)(4,600)(5,600)(5,600)(5,1200)(5,600)(3,600)(3,600)(3,600)(4,600)(2,600)(2,600)(2,600)(1,600)(3,600)(5,600)(5,600)(3,600)(3,600)(3,1200)(2,600)(2,600)(2,600)(2,600)(2,600)(3,600)(4,1200)(3,600)(3,600)(3,600)(3,600)(3,600)(4,600)(5,1200)(5,600)(3,600)(3,600)(3,600)(4,600)(2,600)(2,600)(2,600)(1,600)(3,600)(5,600)(5,600)(3,600)(3,600)(3,1200)", 20);
    }
*/
}