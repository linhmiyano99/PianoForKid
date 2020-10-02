package com.example.pianoforkid.data.key;


import android.media.MediaPlayer;
import android.os.AsyncTask;

import com.example.pianoforkid.R;


public class PlayNoteTask extends AsyncTask<KeyNote.MyTaskParams, Void, Void> {


    @Override
    protected Void doInBackground(KeyNote.MyTaskParams... myTaskParams) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        switch (myTaskParams[0].note){
            case 1:
                mediaPlayer = MediaPlayer.create(myTaskParams[0].context, R.raw.c1);
                break;
            case 2:
                mediaPlayer = MediaPlayer.create(myTaskParams[0].context, R.raw.d1);
                break;
            case 3:
                mediaPlayer = MediaPlayer.create(myTaskParams[0].context, R.raw.e1);
                break;
            case 4:
                mediaPlayer = MediaPlayer.create(myTaskParams[0].context, R.raw.f1);
                break;
            case 5:
                mediaPlayer = MediaPlayer.create(myTaskParams[0].context, R.raw.g1);
                break;
            case 6:
                mediaPlayer = MediaPlayer.create(myTaskParams[0].context, R.raw.a1);
                break;
            case 7:
                mediaPlayer = MediaPlayer.create(myTaskParams[0].context, R.raw.b1);
                break;
            case 8:
                mediaPlayer = MediaPlayer.create(myTaskParams[0].context, R.raw.c2);
                break;
            case 9:
                mediaPlayer = MediaPlayer.create(myTaskParams[0].context, R.raw.d2);
                break;
            case 10:
                mediaPlayer = MediaPlayer.create(myTaskParams[0].context, R.raw.e2);
                break;
            case 11:
                mediaPlayer = MediaPlayer.create(myTaskParams[0].context, R.raw.f2);
                break;
            case 12:
                mediaPlayer = MediaPlayer.create(myTaskParams[0].context, R.raw.g2);
                break;
            case 13:
                mediaPlayer = MediaPlayer.create(myTaskParams[0].context, R.raw.a2);
                break;
            case 14:
                mediaPlayer = MediaPlayer.create(myTaskParams[0].context, R.raw.b2);
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