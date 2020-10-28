package com.example.pianoforkid.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.pianoforkid.R;
import com.example.pianoforkid.data.key.KeyNote;
import com.example.pianoforkid.data.model.Sound;
import com.example.pianoforkid.ultis.ConvertSong;
import com.example.pianoforkid.viewmodel.SongViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlayMusicWithInstructionActivity extends AppCompatActivity {

    ImageView imageC;
    ImageView imageD;
    ImageView imageE;
    ImageView imageF;
    ImageView imageG;
    ImageView imageA;
    ImageView imageB;
    ImageView imageC2;
    ImageView imageD2;
    ImageView imageE2;
    ImageView imageF2;
    ImageView imageG2;
    ImageView imageA2;
    ImageView imageB2;

    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    Button btn9;
    Button btn10;
    Button btn11;
    Button btn12;
    Button btn13;
    Button btn14;

    int onUp;
    int onDown;

    List<Sound> song;
    int lastNote = 0;

    int songId;
    SongViewModel viewModel;

    Animation anim_not_nhac;

    KeyNote keyNote;


    public static void startActivity(Context context, int id){
        Intent intent = new Intent(context, PlayMusicWithInstructionActivity.class);
        intent.putExtra("songId", id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music_with_instruction);

        Log.d("PlayMusicWithActivity", "onCreate");

        keyNote = new KeyNote(this.getApplication());
        imageC = findViewById(R.id.imageView1);
        imageD = findViewById(R.id.imageView2);
        imageE = findViewById(R.id.imageView3);
        imageF = findViewById(R.id.imageView4);
        imageG = findViewById(R.id.imageView5);
        imageA = findViewById(R.id.imageView6);
        imageB = findViewById(R.id.imageView7);
        imageC2 = findViewById(R.id.imageView8);
        imageD2 = findViewById(R.id.imageView9);
        imageE2 = findViewById(R.id.imageView10);
        imageF2 = findViewById(R.id.imageView11);
        imageG2 = findViewById(R.id.imageView12);
        imageA2 = findViewById(R.id.imageView13);
        imageB2 = findViewById(R.id.imageView14);

        btn1 = findViewById(R.id.btnC1);
        btn2 = findViewById(R.id.btnD1);
        btn3 = findViewById(R.id.btnE1);
        btn4 = findViewById(R.id.btnF1);
        btn5 = findViewById(R.id.btnG1);
        btn6 = findViewById(R.id.btnA1);
        btn7 = findViewById(R.id.btnB1);
        btn8 = findViewById(R.id.btnC2);
        btn9 = findViewById(R.id.btnD2);
        btn10 = findViewById(R.id.btnE2);
        btn11 = findViewById(R.id.btnF2);
        btn12 = findViewById(R.id.btnG2);
        btn13 = findViewById(R.id.btnA2);
        btn14 = findViewById(R.id.btnB2);

        ImageButton button_back = findViewById(R.id.button_back);
        anim_not_nhac= AnimationUtils.loadAnimation(this,R.anim.anim_key_board);
        button_back.setOnClickListener(v -> onBackPressed());


        songId = 1;
        if(getIntent().getExtras() != null) {
            songId = Objects.requireNonNull(getIntent().getExtras()).getInt("songId");
            try{
                viewModel.loadSongById(songId);
            }catch (Exception e){
                System.out.println(e);
            }

        }        Log.d("TAG Song", String.valueOf(songId));
        song = new ArrayList<>();
        viewModel = new ViewModelProvider(this).get(SongViewModel.class);
        viewModel.getCurrentSong().observe(this, soundList -> {
            if (soundList.size() > 0) {
                song.addAll(soundList);
                play();
            }
        });
       /* viewModel.getSongById(songId).observe(this, songById->{
            if(songById.sheet!=null) {
                song = ConvertSong.getConvertStringSongToSound(songById.sheet, songId);
                play();
            }
        });*/

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        onUp = (int) imageA.getY();
        onDown = onUp - 100;
        resetAllNote();
    }

    void play() {
        if (song.size()== 0)
            return;

        final Sound sound = song.get(0);
        song.remove(0);
        upNote(sound.getNote());
        new Handler().postDelayed(this::play, sound.getDuration());
    }


    @Override
    public void onBackPressed() {
        song.clear();
        super.onBackPressed();
    }
    void upNote(int note){
        keyNote.playNote(note);
        resetAllNote();
        clearAni(lastNote);
        setNoteAni(note);
        switch (note) {
            case 1:
                imageC.setY(onDown);
                break;
            case 2:
                imageD.setY(onDown);
                break;
            case 3:
                imageE.setY(onDown);
                break;
            case 4:
                imageF.setY(onDown);
                break;
            case 5:
                imageG.setY(onDown);
                break;
            case 6:
                imageA.setY(onDown);
                break;
            case 7:
                imageB.setY(onDown);
                break;
            case 8:
                imageC2.setY(onDown);
                break;
            case 9:
                imageD2.setY(onDown);
                break;
            case 10:
                imageE2.setY(onDown);
                break;
            case 11:
                imageF2.setY(onDown);
                break;
            case 12:
                imageG2.setY(onDown);
                break;
            case 13:
                imageA2.setY(onDown);
                break;
            case 14:
                imageB2.setY(onDown);
                break;
        }
        lastNote = note;

    }
    void resetAllNote(){
        imageC.setY(onUp);
        imageD.setY(onUp);
        imageE.setY(onUp);
        imageF.setY(onUp);
        imageG.setY(onUp);
        imageA.setY(onUp);
        imageB.setY(onUp);
        imageC2.setY(onUp);
        imageD2.setY(onUp);
        imageE2.setY(onUp);
        imageF2.setY(onUp);
        imageG2.setY(onUp);
        imageA2.setY(onUp);
        imageB2.setY(onUp);
    }

    void setNoteAni(int note){
        switch (note){
            case 1: btn1.startAnimation(anim_not_nhac); imageC.startAnimation(anim_not_nhac); break;
            case 2: btn2.startAnimation(anim_not_nhac);imageD.startAnimation(anim_not_nhac);break;
            case 3: btn3.startAnimation(anim_not_nhac);imageE.startAnimation(anim_not_nhac);break;
            case 4: btn4.startAnimation(anim_not_nhac);imageF.startAnimation(anim_not_nhac);break;
            case 5: btn5.startAnimation(anim_not_nhac);imageG.startAnimation(anim_not_nhac);break;
            case 6: btn6.startAnimation(anim_not_nhac);imageA.startAnimation(anim_not_nhac);break;
            case 7: btn7.startAnimation(anim_not_nhac);imageB.startAnimation(anim_not_nhac);break;
            case 8: btn8.startAnimation(anim_not_nhac);imageC2.startAnimation(anim_not_nhac);break;
            case 9: btn9.startAnimation(anim_not_nhac);imageD2.startAnimation(anim_not_nhac);break;
            case 10: btn10.startAnimation(anim_not_nhac);imageE2.startAnimation(anim_not_nhac);break;
            case 11: btn11.startAnimation(anim_not_nhac);imageF2.startAnimation(anim_not_nhac);break;
            case 12: btn12.startAnimation(anim_not_nhac);imageG2.startAnimation(anim_not_nhac);break;
            case 13: btn13.startAnimation(anim_not_nhac);imageA2.startAnimation(anim_not_nhac);break;
            case 14: btn14.startAnimation(anim_not_nhac);imageB2.startAnimation(anim_not_nhac);break;
        }
    }
    void clearAni(int note){
        switch (note){
            case 1 : btn1.clearAnimation();imageC.clearAnimation();break;
            case 2 : btn2.clearAnimation();imageD.clearAnimation();break;
            case 3 : btn3.clearAnimation();imageE.clearAnimation();break;
            case 4 : btn4.clearAnimation();imageF.clearAnimation();break;
            case 5 : btn5.clearAnimation();imageG.clearAnimation();break;
            case 6 : btn6.clearAnimation();imageA.clearAnimation();break;
            case 7 : btn7.clearAnimation();imageB.clearAnimation();break;
            case 8 : btn8.clearAnimation();imageC2.clearAnimation();break;
            case 9 : btn9.clearAnimation();imageD2.clearAnimation();break;
            case 10 : btn10.clearAnimation();imageE2.clearAnimation();break;
            case 11 : btn11.clearAnimation();imageF2.clearAnimation();break;
            case 12 : btn12.clearAnimation();imageG2.clearAnimation();break;
            case 13 : btn13.clearAnimation();imageA2.clearAnimation();break;
            case 14 : btn14.clearAnimation();imageB2.clearAnimation();break;
        }
    }

}
