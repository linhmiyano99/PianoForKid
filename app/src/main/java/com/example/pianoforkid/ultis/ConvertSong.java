package com.example.pianoforkid.ultis;

import com.example.pianoforkid.data.model.Sound;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConvertSong {

	public static String getConvertSoundToStringSong(List<Sound> listSound){
        String song = null;
        for (Sound s: listSound
             ) {
            song += s.toString();
        }
        return song;
    }

    public static List<Sound> getConvertStringSongToSound(String song, int songId){
        List<Sound> soundList = new ArrayList<>();
        while (song.length() > 0){
            Pattern pattern = Pattern.compile("([1-20],[1-999999])");
            Matcher matcher = pattern.matcher(song);
            if(matcher.find()){
                String temp = (String) matcher.group().subSequence(0, matcher.group().length());
				soundList.add(splitString(temp, songId));
            }
            else {
            	break;
            }
            song = song.substring(matcher.end());
        }
        return soundList;
    }
    static Sound splitString(String sound, int songId) {
    	String sNote = "";
        int note = 0;
        String sDuration = "";
        long duration = 0;
        Sound s = null;
        boolean isNote = true;
        for (char i : sound.toCharArray()) {
        if(i == ',') {
        	 isNote = false;
        	 continue;
        }
        if(isNote) {
        	sNote+=i;
        }
        else {
        	sDuration+=i;
         	}
        }
		note = Integer.parseInt(sNote); 
		duration =Long.parseLong(sDuration);
		 
        s = new Sound(songId, note, duration);
         return s;
    }

}
