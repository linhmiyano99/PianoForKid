package com.example.pianoforkid.data.key;

import android.app.Application;
import android.content.Context;

public class KeyNote {

    private Context context;

    public KeyNote(Application application){
        this.context = application;
    }


    public void playNote(int note){
        PlayNoteTask task = new PlayNoteTask();
        //this to set delegate / listener back to this class
        MyTaskParams params = new MyTaskParams(note,context);
        task.execute(params);
    }

    public static class MyTaskParams {
        public int note;
        public Context context;

        MyTaskParams(int note, Context context) {
            this.note = note;
            this.context = context;
        }
    }
}


