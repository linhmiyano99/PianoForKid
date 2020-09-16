package com.e.happpymusic.data.key;


import android.media.MediaPlayer;
import android.os.AsyncTask;

import com.example.happymusic.R;


public class PlayNoteTask extends AsyncTask<KeyNote.MyTaskParams, Void, Void> {


    @Override
    protected Void doInBackground(KeyNote.MyTaskParams... myTaskParams) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        switch (myTaskParams[0].note){
            case 1:
                mediaPlayer = MediaPlayer.create(myTaskParams[0].context, R.raw.p001);
                break;
            case 2:
                mediaPlayer = MediaPlayer.create(myTaskParams[0].context, R.raw.p002);
                break;
            case 3:
                mediaPlayer = MediaPlayer.create(myTaskParams[0].context, R.raw.p003);
                break;
            case 4:
                mediaPlayer = MediaPlayer.create(myTaskParams[0].context, R.raw.p004);
                break;
            case 5:
                mediaPlayer = MediaPlayer.create(myTaskParams[0].context, R.raw.p005);
                break;
            case 6:
                mediaPlayer = MediaPlayer.create(myTaskParams[0].context, R.raw.p006);
                break;
            case 7:
                mediaPlayer = MediaPlayer.create(myTaskParams[0].context, R.raw.p007);
                break;
            case 8:
                mediaPlayer = MediaPlayer.create(myTaskParams[0].context, R.raw.p008);
                break;
            case 9:
                mediaPlayer = MediaPlayer.create(myTaskParams[0].context, R.raw.p009);
                break;
            case 10:
                mediaPlayer = MediaPlayer.create(myTaskParams[0].context, R.raw.p010);
                break;
            case 11:
                mediaPlayer = MediaPlayer.create(myTaskParams[0].context, R.raw.p011);
                break;
            case 12:
                mediaPlayer = MediaPlayer.create(myTaskParams[0].context, R.raw.p012);
                break;
            case 13:
                mediaPlayer = MediaPlayer.create(myTaskParams[0].context, R.raw.p013);
                break;
            case 14:
                mediaPlayer = MediaPlayer.create(myTaskParams[0].context, R.raw.p014);
                break;
        }
        if(mediaPlayer != null) {
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                }
            });
        }

        return null;
    }
}