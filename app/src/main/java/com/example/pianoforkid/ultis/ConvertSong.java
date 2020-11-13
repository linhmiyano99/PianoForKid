package com.example.pianoforkid.ultis;

import android.util.Log;

import com.example.pianoforkid.data.model.Sound;

import java.util.ArrayList;
import java.util.List;

public class ConvertSong {

    public static String getConvertSoundToStringSong(List<Sound> listSound) {
        StringBuilder song = new StringBuilder();
        for (Sound s : listSound
        ) {
            song.append(s.toString());
        }
        return song.toString();
    }

    public static List<Sound> getConvertStringSongToSound(String song, int songId) {
        List<Sound> soundList = new ArrayList<>();
        StringBuilder tempSong = new StringBuilder();
        for (char c : song.toCharArray()) {
            if (c == '(') {
                continue;
            }
            if (c == ')') {
                soundList.add(splitString(String.valueOf(tempSong), songId));
                tempSong = new StringBuilder();
                continue;
            }
            tempSong.append(c);
        }
        return soundList;
    }

    static Sound splitString(String sound, int songId) {
        StringBuilder sNote = new StringBuilder();
        int note = 0;
        StringBuilder sDuration = new StringBuilder();
        long duration = 0;
        Sound s = null;
        boolean isNote = true;
        Log.d("xxsound", sound);

        for (char i : sound.toCharArray()) {
            if (i == ',') {
                isNote = false;
                continue;
            }
            if (isNote) {
                sNote.append(i);
            } else {
                sDuration.append(i);
                Log.d("xxduration", sDuration.toString());
            }
        }
        note = Integer.parseInt(sNote.toString());
        Log.d("xxnote", sNote.toString());
        duration = Long.parseLong(sDuration.toString());
        Log.d("xxduảtion", sDuration.toString());


        s = new Sound(songId, note, duration);
        return s;
    }

}
