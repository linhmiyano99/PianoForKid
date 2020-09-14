package com.example.pianoforkid.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pianoforkid.R;

public class SongListActivity extends AppCompatActivity {

    public static void startActivity(Context context){
        Intent intent = new Intent(context, SongListActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set view layout for this activity
        setContentView(R.layout.activity_song_list);
    }


}