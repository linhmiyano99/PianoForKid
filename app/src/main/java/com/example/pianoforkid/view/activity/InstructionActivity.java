package com.example.pianoforkid.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;

import com.example.pianoforkid.R;
import com.example.pianoforkid.data.key.KeyNote;
import com.example.pianoforkid.data.model.Sound;
import com.example.pianoforkid.viewmodel.SongViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InstructionActivity extends AppCompatActivity {

    ImageView image1;
    ImageView image2;
    ImageView image3;
    ImageView image4;
    ImageView image5;
    ImageView image6;
    ImageView image7;
    int position;
    int lastNote;
    List<Sound> song;
    List<Sound> songTemp;
    SongViewModel viewModel;
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
    ImageButton button_back;

    int songId;
    int songSize;
    int fault;
    int sizeTemp;

    float onUp2;
    float onDown2;
    private Animation anim_not_nhac;
    int songLeft =0;

    KeyNote keyNote;

    public static void startActivity(Context context, int id){
        Intent intent = new Intent(context, InstructionActivity.class);
        intent.putExtra("songId", id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);

        Log.d("InstructionActivity", "onCreate");

        keyNote = new KeyNote(this.getApplication());
        anim_not_nhac= AnimationUtils.loadAnimation(this,R.anim.anim_key_board);

        image1 = findViewById(R.id.imageView1);
        image2 = findViewById(R.id.imageView2);
        image3 = findViewById(R.id.imageView3);
        image4 = findViewById(R.id.imageView4);
        image5 = findViewById(R.id.imageView5);
        image6 = findViewById(R.id.imageView6);
        image7 = findViewById(R.id.imageView7);

        image1.setTag(0);
        image2.setTag(0);
        image3.setTag(0);
        image4.setTag(0);
        image5.setTag(0);
        image6.setTag(0);
        image7.setTag(0);

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

        button_back = findViewById(R.id.button_back);
        fault = 0;
        songSize = 0;
        songId = 1;
        sizeTemp = 0;
        songId = Objects.requireNonNull(getIntent().getExtras()).getInt("songId");
        Log.d("TAG Song", String.valueOf(songId));
        song = new ArrayList<>();
        songTemp = new ArrayList<>();

        lastNote = 0;
        viewModel = new ViewModelProvider(this).get(SongViewModel.class);
        viewModel.loadSongById(songId);
        viewModel.getCurrentSong().observe(this, soundList -> {
            if (soundList.size() > 0) {
                song.addAll(soundList);
                songTemp.addAll(song);
                Log.d("TAG", String.valueOf(song.size()));
                Log.d("TAG", String.valueOf(song));
                songSize = song.size();
                songLeft = songSize;
                updateView();

                setNoteAni(song.get(0).note);
                image1.startAnimation(anim_not_nhac);
            }
        });
        position = 0;


        btn1.setOnClickListener(this::check);
        btn2.setOnClickListener(this::check);
        btn3.setOnClickListener(this::check);
        btn4.setOnClickListener(this::check);
        btn5.setOnClickListener(this::check);
        btn6.setOnClickListener(this::check);
        btn7.setOnClickListener(this::check);
        btn8.setOnClickListener(this::check);
        btn9.setOnClickListener(this::check);
        btn10.setOnClickListener(this::check);
        btn11.setOnClickListener(this::check);
        btn12.setOnClickListener(this::check);
        btn13.setOnClickListener(this::check);
        btn14.setOnClickListener(this::check);


        onUp2 = image1.getTop() - 40;
        resetAllNote();
        onUp2 = image1.getTop() + 30;
        onDown2 = onUp2 - 30;
        image1.setY(onDown2 - 80);

        button_back.setOnClickListener(v -> {
            /*Intent goBackToMainMenu = new Intent(this, MainMenuActivity.class);
            startActivity(goBackToMainMenu);*/
            onBackPressed();

        });

    }
    public void check(View view) {
        Log.d("[sizeTemp]", String.valueOf(sizeTemp));
        Log.d("[songSize]", String.valueOf(songSize));
        Log.d("[songSize]", String.valueOf(song.size()));

        int currentId = getCurrentId(view);

   /*     soundPlayer.stopNote(lastNote);
        soundPlayer.playNote(currentId);*/
        keyNote.playNote(currentId);

        lastNote = currentId;

        if (song.size() == 0){
            return;
        }
        // String x = String.valueOf(listNote.charAt(position));
        int x = song.get(position).note;
        Log.d("TAG", "hello");
        Log.d("TAG", String.valueOf(currentId));

        if (currentId == x) {
            clearAni(x);
            songLeft--;
            Log.d("[sizeTemp2]", String.valueOf(sizeTemp));
            Log.d("[sizeTemp2]", String.valueOf(sizeTemp));
            Log.d("[songSize2]", String.valueOf(songSize));
            Log.d("[songSize2]", String.valueOf(song.size()));
            sizeTemp++;
            position++;
            if (position >= 7) {
                position = 0;
                try {
                    song.subList(0, 7).clear();
                    updateView();

                } catch (Exception ignored) {

                }
            }

            switch (position) {
                case 0:
                    image1.setY(onDown2);
                    image7.setY(onUp2);
                    setNoteAni((Integer) image1.getTag());
                    image1.startAnimation(anim_not_nhac);
                    image7.clearAnimation();
                    // animation1.start();
                    break;
                case 1:
                    image2.setY(onDown2);
                    image1.setY(onUp2);
                    setNoteAni((Integer) image2.getTag());
                    image2.startAnimation(anim_not_nhac);
                    image1.clearAnimation();

                    // animation2.start();
                    break;
                case 2:
                    image3.setY(onDown2);
                    image2.setY(onUp2);
                    setNoteAni((Integer) image3.getTag());
                    image3.startAnimation(anim_not_nhac);
                    image2.clearAnimation();
                    // animation3.start();
                    break;
                case 3:
                    image4.setY(onDown2);
                    image3.setY(onUp2);
                    setNoteAni((Integer) image4.getTag());
                    image4.startAnimation(anim_not_nhac);
                    image3.clearAnimation();
                    // animation4.start();
                    break;
                case 4:
                    image5.setY(onDown2);
                    image4.setY(onUp2);
                    setNoteAni((Integer) image5.getTag());
                    image5.startAnimation(anim_not_nhac);
                    image4.clearAnimation();
                    // animation5.start();
                    break;
                case 5:
                    image6.setY(onDown2);
                    image5.setY(onUp2);
                    setNoteAni((Integer) image6.getTag());
                    image6.startAnimation(anim_not_nhac);
                    image5.clearAnimation();
                    // animation6.start();
                    break;
                case 6:
                    image7.setY(onDown2);
                    image6.setY(onUp2);
                    setNoteAni((Integer) image7.getTag());
                    image7.startAnimation(anim_not_nhac);
                    image6.clearAnimation();
                    // animation7.start();
                    break;
            }
            if(sizeTemp == songSize){
                Log.d("[SCORE]", String.valueOf(getScore()));
                showAlertDialogButtonClicked();
            }
        }
        else{
            fault++;
        }
    }

    public void updateView() {
        if (song.size() > 0) {
            if(song.size() >= 7) {
                image7.setImageBitmap(getImageOfNote(song.get(6).note));
                image7.setTag(song.get(6).note);
            }
            else {
                image7.setImageResource(android.R.color.transparent);
            }
            if(song.size() >= 6) {
                image6.setImageBitmap(getImageOfNote(song.get(5).note));
                image6.setTag(song.get(5).note);
            }
            else {
                image6.setImageResource(android.R.color.transparent);
            }
            if(song.size() >= 5) {
                image5.setImageBitmap(getImageOfNote(song.get(4).note));
                image5.setTag(song.get(4).note);
            }
            else{
                image5.setImageResource(android.R.color.transparent);
            }
            if(song.size() >= 4) {
                image4.setImageBitmap(getImageOfNote(song.get(3).note));
                image4.setTag(song.get(3).note);
            }
            else {
                image4.setImageResource(android.R.color.transparent);
            }
            if(song.size() >= 3) {
                image3.setImageBitmap(getImageOfNote(song.get(2).note));
                image3.setTag(song.get(2).note);
            }
            else {
                image3.setImageResource(android.R.color.transparent);
            }
            if(song.size() >= 2) {
                image2.setImageBitmap(getImageOfNote(song.get(1).note));
                image2.setTag(song.get(1).note);
            }
            else {
                image2.setImageResource(android.R.color.transparent);
            }
            if(song.size() >= 1) {
                image1.setImageBitmap(getImageOfNote(song.get(0).note));
                image1.setTag(song.get(0).note);
            }
            else {
                image1.setImageResource(android.R.color.transparent);
            }
        }
    }

    public Bitmap getImageOfNote(int note) {
        switch (note) {

            case 1:
                return BitmapFactory.decodeResource(getResources(), R.drawable.star_c1);
            case 2:
                return BitmapFactory.decodeResource(getResources(), R.drawable.star_d1);
            case 3:
                return BitmapFactory.decodeResource(getResources(), R.drawable.star_e1);
            case 4:
                return BitmapFactory.decodeResource(getResources(), R.drawable.star_f1);
            case 5:
                return BitmapFactory.decodeResource(getResources(), R.drawable.star_g1);
            case 6:
                return BitmapFactory.decodeResource(getResources(), R.drawable.star_a1);
            case 7:
                return BitmapFactory.decodeResource(getResources(), R.drawable.star_b1);
            case 8:
                return BitmapFactory.decodeResource(getResources(), R.drawable.star_c2);
            case 9:
                return BitmapFactory.decodeResource(getResources(), R.drawable.star_d2);
            case 10:
                return BitmapFactory.decodeResource(getResources(), R.drawable.star_e2);
            case 11:
                return BitmapFactory.decodeResource(getResources(), R.drawable.star_f2);
            case 12:
                return BitmapFactory.decodeResource(getResources(), R.drawable.star_g2);
            case 13:
                return BitmapFactory.decodeResource(getResources(), R.drawable.star_a2);
            case 14:
                return BitmapFactory.decodeResource(getResources(), R.drawable.star_b2);
        }
        return null;
    }

    int getCurrentId(View view) {
        switch (view.getId()) {
            case R.id.btnC1:
                Log.d("You chose", "btnC1");
                return 1;
            case R.id.btnD1:
                Log.d("You chose", "btnD1");
                return 2;
            case R.id.btnE1:
                Log.d("You chose", "btnE1");
                return 3;
            case R.id.btnF1:
                Log.d("You chose", "btnF1");

                return 4;
            case R.id.btnG1:
                Log.d("You chose", "btnG1");

                return 5;
            case R.id.btnA1:
                Log.d("You chose", "btnA1");

                return 6;
            case R.id.btnB1:
                Log.d("You chose", "btnB1");

                return 7;

            case R.id.btnC2:
                Log.d("You chose", "btnC2");

                return 8;
            case R.id.btnD2:
                Log.d("You chose", "btnD2");

                return 9;
            case R.id.btnE2:
                Log.d("You chose", "btnE2");

                return 10;
            case R.id.btnF2:
                Log.d("You chose", "btnF2");

                return 11;
            case R.id.btnG2:
                Log.d("You chose", "btnG2");

                return 12;
            case R.id.btnA2:
                Log.d("You chose", "btnA2");

                return 13;
            case R.id.btnB2:
                Log.d("You chose", "btnB2");
                return 14;
        }
        return 1;
    }

    void resetinstruction(){
        fault = 0;
        position = 0;
        sizeTemp = 0;
        song.clear();
        Log.d("[XXXXXXXXXXXX]", String.valueOf(song.size()));

        song.addAll(songTemp);
        resetAllNote();
        updateView();
/*        for(int i = 0; i<14;i++){
            if (soundPlayer.isNotePlaying(i)) {
                soundPlayer.stopNote(i);
            }
        }*/
        image1.clearAnimation();
        image2.clearAnimation();
        image3.clearAnimation();
        image4.clearAnimation();
        image5.clearAnimation();
        image6.clearAnimation();
        image7.clearAnimation();

        btn1.clearAnimation();
        btn2.clearAnimation();
        btn3.clearAnimation();
        btn4.clearAnimation();
        btn5.clearAnimation();
        btn6.clearAnimation();
        btn7.clearAnimation();
        btn8.clearAnimation();
        btn9.clearAnimation();
        btn10.clearAnimation();
        btn11.clearAnimation();
        btn12.clearAnimation();
        btn13.clearAnimation();
        btn14.clearAnimation();
    }

    int getScore(){
        if(songSize != 0)
        {
            if(fault * 1.0f/songSize < 0.4)
                return 100;
            if(fault * 1.0f/songSize < 0.7)
                return 60;
            if(fault * 1.0f/songSize < 1)
                return 30;
            return 0;
        }
        return 0;
    }
    public void showAlertDialogButtonClicked() {
        // create an alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // set the custom layout
        final View customLayout = getLayoutInflater().inflate(R.layout.dialog_congratulation, null);
        builder.setView(customLayout);
        // add a button

        CardView replay =  customLayout.findViewById(R.id.card_view_replay);
        CardView cancel =  customLayout.findViewById(R.id.card_view_cancel);
/*        ImageView star1 = customLayout.findViewById(R.id.image_star1);
        ImageView star2 = customLayout.findViewById(R.id.image_star2);
        ImageView star3 = customLayout.findViewById(R.id.image_star3);*/


        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();

      /*  int score = getScore();
        if(score == 100){
            star1.setImageResource(R.drawable.star_true);
            star2.setImageResource(R.drawable.star_true);
            star3.setImageResource(R.drawable.star_true);
        }
        else if (score == 60){
            star1.setImageResource(R.drawable.star_true);
            star2.setImageResource(R.drawable.star_true);
            star3.setImageResource(R.drawable.star_auto);
        }
        else if(score == 30){
            star1.setImageResource(R.drawable.star_true);
            star2.setImageResource(R.drawable.star_auto);
            star3.setImageResource(R.drawable.star_auto);
        }
        else{
            star1.setImageResource(R.drawable.star_auto);
            star2.setImageResource(R.drawable.star_auto);
            star3.setImageResource(R.drawable.star_auto);
        }*/
        replay.startAnimation(anim_not_nhac);
        replay.setOnClickListener(v -> {
            resetinstruction();
            dialog.cancel();
            setNoteAni((Integer) image1.getTag());
            resetAllNote();
            replay.clearAnimation();
            image1.startAnimation(anim_not_nhac);
            image1.setY(onDown2);
            songLeft = songSize;
            dialog.cancel();
        });
        cancel.setOnClickListener(v -> {
            replay.clearAnimation();
            onBackPressed();
        });
    }
    void resetAllNote(){
        image1.setY(onUp2);
        image2.setY(onUp2);
        image3.setY(onUp2);
        image4.setY(onUp2);
        image5.setY(onUp2);
        image6.setY(onUp2);
        image7.setY(onUp2);

        image1.clearAnimation();
        image2.clearAnimation();
        image3.clearAnimation();
        image4.clearAnimation();
        image5.clearAnimation();
        image6.clearAnimation();
        image7.clearAnimation();

        btn1.clearAnimation();
        btn2.clearAnimation();
        btn3.clearAnimation();
        btn4.clearAnimation();
        btn5.clearAnimation();
        btn6.clearAnimation();
        btn7.clearAnimation();
        btn8.clearAnimation();
        btn9.clearAnimation();
        btn10.clearAnimation();
        btn11.clearAnimation();
        btn12.clearAnimation();
        btn13.clearAnimation();
        btn14.clearAnimation();
    }
    void setNoteAni(int note){
        switch (note){
            case 1: btn1.startAnimation(anim_not_nhac);break;
            case 2: btn2.startAnimation(anim_not_nhac);break;
            case 3: btn3.startAnimation(anim_not_nhac);break;
            case 4: btn4.startAnimation(anim_not_nhac);break;
            case 5: btn5.startAnimation(anim_not_nhac);break;
            case 6: btn6.startAnimation(anim_not_nhac);break;
            case 7: btn7.startAnimation(anim_not_nhac);break;
            case 8: btn8.startAnimation(anim_not_nhac);break;
            case 9: btn9.startAnimation(anim_not_nhac);break;
            case 10: btn10.startAnimation(anim_not_nhac);break;
            case 11: btn11.startAnimation(anim_not_nhac);break;
            case 12: btn12.startAnimation(anim_not_nhac);break;
            case 13: btn13.startAnimation(anim_not_nhac);break;
            case 14: btn14.startAnimation(anim_not_nhac);break;
        }
    }
    void clearAni(int note){
        switch (note){
            case 1 : btn1.clearAnimation();break;
            case 2 : btn2.clearAnimation();break;
            case 3 : btn3.clearAnimation();break;
            case 4 : btn4.clearAnimation();break;
            case 5 : btn5.clearAnimation();break;
            case 6 : btn6.clearAnimation();break;
            case 7 : btn7.clearAnimation();break;
            case 8 : btn8.clearAnimation();break;
            case 9 : btn9.clearAnimation();break;
            case 10 : btn10.clearAnimation();break;
            case 11 : btn11.clearAnimation();break;
            case 12 : btn12.clearAnimation();break;
            case 13 : btn13.clearAnimation();break;
            case 14 : btn14.clearAnimation();break;
        }
    }

}
