package com.example.pianoforkid.view.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pianoforkid.R;
import com.example.pianoforkid.data.key.KeyNote;
import com.example.pianoforkid.data.model.Sound;
import com.example.pianoforkid.data.model.User;
import com.example.pianoforkid.viewmodel.FirebaseViewModel;
import com.example.pianoforkid.viewmodel.SongViewModel;
import com.example.pianoforkid.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class CompetitionActivity extends AppCompatActivity {

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
    FirebaseViewModel firebaseViewModel;
    UserViewModel userViewModel;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    ImageButton button_back;
    TextView txt_user_score;
    TextView txt_system_score;

    int songId;
    int songSize;
    int fault;
    int sizeTemp;

    int onUp;
    int onDown;
    private Animation anim_not_nhac;
    int songLeft = 0;
    int songSystemLeft = 0;

    KeyNote keyNote;

    User u;
    int done = 0;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, CompetitionActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition);

        Log.d("CompetitionActivity", "onCreate");

        keyNote = new KeyNote(this.getApplication());
        anim_not_nhac= AnimationUtils.loadAnimation(this,R.anim.anim_key_board);

        txt_user_score = findViewById(R.id.txt_user_score);
        txt_system_score = findViewById(R.id.txt_system_score);
        txt_user_score.setText(String.valueOf(songLeft));

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

        button_back = findViewById(R.id.button_back);
        fault = 0;
        songSize = 0;
        songId = 1;
        sizeTemp = 0;
      /*  if(getIntent().getExtras() != null) {
            songId = Objects.requireNonNull(getIntent().getExtras()).getInt("songId");
            try{
                viewModel.loadSongById(songId);
            }catch (Exception e){
                System.out.println(e.toString());
            }
        }*/
        Log.d("TAG Song", String.valueOf(songId));
        song = new ArrayList<>();
        songTemp = new ArrayList<>();

        lastNote = 0;
        viewModel = new ViewModelProvider(this).get(SongViewModel.class);
        firebaseViewModel = new ViewModelProvider(this).get(FirebaseViewModel.class);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        userViewModel.getUser().observe(this, user -> u = user);
        viewModel.loadSongById(1);

        viewModel.getCurrentSong().observe(this, soundList -> {
            if (soundList.size() > 0) {
                song.addAll(soundList);
                songTemp.addAll(song);
                Log.d("TAG", String.valueOf(song.size()));
                Log.d("TAG", String.valueOf(song));
                songSize = song.size();
                songLeft = songSize;
                songSystemLeft = songSize;
                image1.setY(0);
                updateView();
                setNoteAni(song.get(0).note);
                image1.startAnimation(anim_not_nhac);
                txt_user_score.setText(String.valueOf(songLeft));
                if(songLeft > 0) {
                    txt_system_score.setText(String.valueOf(songSystemLeft));
                    systemSoreCount(songLeft);
                }
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

        button_back.setOnClickListener(v -> onBackPressed());

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.d("onWindowFocusChanged", "xx");

        onUp = (int) image2.getY();
        onDown = onUp - 100;

        resetAllNote();
    }

    public void check(View view) {
        int currentId = getCurrentId(view);

   /*     soundPlayer.stopNote(lastNote);
        soundPlayer.playNote(currentId);*/
        keyNote.playNote(currentId);

        lastNote = currentId;

        if (song.size() == 0){
            done = 1;
            return;
        }
        // String x = String.valueOf(listNote.charAt(position));
        int x = song.get(position).note;
        Log.d("TAG", String.valueOf(currentId));

        if (currentId == x) {
            clearAni(x);
            songLeft--;
            txt_user_score.setText(String.valueOf(songLeft));

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
            Log.d("XX_check2_image1", String.valueOf(image1.getY()));
            Log.d("XX_check2_image2", String.valueOf(image2.getY()));
            switch (position) {
                case 0:
                    image1.setY(onDown);
                    image7.setY(onUp);
                    setNoteAni((Integer) image1.getTag());
                    image1.startAnimation(anim_not_nhac);
                    image7.clearAnimation();
                    break;
                case 1:
                    image2.setY(onDown);
                    image1.setY(onUp);
                    setNoteAni((Integer) image2.getTag());
                    image2.startAnimation(anim_not_nhac);
                    image1.clearAnimation();
                    break;
                case 2:
                    image3.setY(onDown);
                    image2.setY(onUp);
                    setNoteAni((Integer) image3.getTag());
                    image3.startAnimation(anim_not_nhac);
                    image2.clearAnimation();
                    break;
                case 3:
                    image4.setY(onDown);
                    image3.setY(onUp);
                    setNoteAni((Integer) image4.getTag());
                    image4.startAnimation(anim_not_nhac);
                    image3.clearAnimation();
                    break;
                case 4:
                    image5.setY(onDown);
                    image4.setY(onUp);
                    setNoteAni((Integer) image5.getTag());
                    image5.startAnimation(anim_not_nhac);
                    image4.clearAnimation();
                    break;
                case 5:
                    image6.setY(onDown);
                    image5.setY(onUp);
                    setNoteAni((Integer) image6.getTag());
                    image6.startAnimation(anim_not_nhac);
                    image5.clearAnimation();
                    break;
                case 6:
                    image7.setY(onDown);
                    image6.setY(onUp);
                    setNoteAni((Integer) image7.getTag());
                    image7.startAnimation(anim_not_nhac);
                    image6.clearAnimation();
                    break;
            }
            if(sizeTemp == songSize){
                Log.d("[SCORE]", String.valueOf(getScore()));
                done = 1;
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
                image7.setImageResource(getImageOfNote(song.get(6).note));
                image7.setTag(song.get(6).note);
            }
            else {
                image7.setImageResource(android.R.color.transparent);
            }
            if(song.size() >= 6) {
                image6.setImageResource(getImageOfNote(song.get(5).note));
                image6.setTag(song.get(5).note);
            }
            else {
                image6.setImageResource(android.R.color.transparent);
            }
            if(song.size() >= 5) {
                image5.setImageResource(getImageOfNote(song.get(4).note));
                image5.setTag(song.get(4).note);
            }
            else{
                image5.setImageResource(android.R.color.transparent);
            }
            if(song.size() >= 4) {
                image4.setImageResource(getImageOfNote(song.get(3).note));
                image4.setTag(song.get(3).note);
            }
            else {
                image4.setImageResource(android.R.color.transparent);
            }
            if(song.size() >= 3) {
                image3.setImageResource(getImageOfNote(song.get(2).note));
                image3.setTag(song.get(2).note);
            }
            else {
                image3.setImageResource(android.R.color.transparent);
            }
            if(song.size() >= 2) {
                image2.setImageResource(getImageOfNote(song.get(1).note));
                image2.setTag(song.get(1).note);
            }
            else {
                image2.setImageResource(android.R.color.transparent);
            }
            if(song.size() >= 1) {
                image1.setImageResource(getImageOfNote(song.get(0).note));
                image1.setTag(song.get(0).note);
                if(image1.getY() != 0){
                    image1.setY(image1.getY()- 100 );
                }
            }
            else {
                image1.setImageResource(android.R.color.transparent);
            }
        }
    }

    public int getImageOfNote(int note) {
        switch (note) {

            case 1:
                return  R.drawable.star_c1;
            case 2:
                return R.drawable.star_d1;
            case 3:
                return  R.drawable.star_e1;
            case 4:
                return R.drawable.star_f1;
            case 5:
                return R.drawable.star_g1;
            case 6:
                return R.drawable.star_a1;
            case 7:
                return R.drawable.star_b1;
            case 8:
                return R.drawable.star_c2;
            case 9:
                return  R.drawable.star_d2;
            case 10:
                return R.drawable.star_e2;
            case 11:
                return R.drawable.star_f2;
            case 12:
                return R.drawable.star_g2;
            case 13:
                return R.drawable.star_a2;
            case 14:
                return  R.drawable.star_b2;
        }
        return R.drawable.star_c1;
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
           /* case R.id.btnG2:
                Log.d("You chose", "btnG2");

                return 12;
            case R.id.btnA2:
                Log.d("You chose", "btnA2");

                return 13;
            case R.id.btnB2:
                Log.d("You chose", "btnB2");
                return 14;*/
        }
        return 1;
    }

    void resetInstruction(){
        fault = 0;
        position = 0;
        sizeTemp = 0;
        song.clear();

        song.addAll(songTemp);
        resetAllNote();
        updateView();
/*        for(int i = 0; i<14;i++){
            if (soundPlayer.isNotePlaying(i)) {
                soundPlayer.stopNote(i);
            }
        }*/
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
    @SuppressLint({"SetTextI18n", "InflateParams"})
    public void showAlertDialogButtonClicked() {
        // create an alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // set the custom layout
        final View customLayout;
        if(done == 1) {
            customLayout = getLayoutInflater().inflate(R.layout.dialog_win, null);
        }
        else {
            customLayout = getLayoutInflater().inflate(R.layout.dialog_game_over, null);
        }
           builder.setView(customLayout);
        // add a button

        CardView replay =  customLayout.findViewById(R.id.card_view_replay);
        CardView cancel =  customLayout.findViewById(R.id.card_view_cancel);


        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();

        replay.startAnimation(anim_not_nhac);
        replay.setOnClickListener(v -> {
            resetInstruction();
            dialog.cancel();
            setNoteAni((Integer) image1.getTag());
            resetAllNote();
            replay.clearAnimation();
            image1.startAnimation(anim_not_nhac);
            image1.setY(onDown);
            songLeft = songSize;
            songSystemLeft = songSize;
            txt_user_score.setText(Integer.toString(songSize));
            txt_system_score.setText(Integer.toString(songSize));
            systemSoreCount(songSize);
            dialog.cancel();

        });
        cancel.setOnClickListener(v -> {
            replay.clearAnimation();
            dialog.cancel();
            onBackPressed();
        });
    }
    void resetAllNote(){
        Log.d("resetAllNote", "xx");

        image1.setY(onDown);
        image2.setY(onUp);
        image3.setY(onUp);
        image4.setY(onUp);
        image5.setY(onUp);
        image6.setY(onUp);
        image7.setY(onUp);

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
        }
    }

    void systemSoreCount(int max){
        if(max > 0) {
            new CountDownTimer(max * 1000, 1000) {
                @SuppressLint("SetTextI18n")
                public void onTick(long millisUntilFinished) {
                    txt_system_score.setText(Integer.toString((int) millisUntilFinished / 1000));
                }

                @SuppressLint("SetTextI18n")
                public void onFinish() {
                    txt_system_score.setText(Integer.toString(0));
                    done = -1;
                    showAlertDialogButtonClicked();
                }
            }.start();
        }
    }
}