package com.example.pianoforkid.view.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.pianoforkid.R;
import com.example.pianoforkid.data.model.LikedSong;
import com.example.pianoforkid.data.model.Song;
import com.example.pianoforkid.view.adaper.LessonListAdapter;
import com.example.pianoforkid.view.adaper.SongListAdapter;
import com.example.pianoforkid.viewmodel.FirebaseViewModel;
import com.example.pianoforkid.viewmodel.SongViewModel;

import java.util.Objects;

public class LessonActivity extends AppCompatActivity {
    public LessonListAdapter lessonListAdapter;
    public SongListAdapter songListAdapter;
    FirebaseViewModel viewModel;
    SongViewModel localViewModel;
    RecyclerView recyclerView;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, LessonActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        viewModel = new ViewModelProvider(this).get(FirebaseViewModel.class);
        localViewModel = new ViewModelProvider(this).get(SongViewModel.class);

        recyclerView= findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        lessonListAdapter = new LessonListAdapter();
        songListAdapter = new SongListAdapter();
        recyclerView.setAdapter(lessonListAdapter);
        viewModel.loadListLesson();
        viewModel.getListLessons().observe(this, songs->lessonListAdapter.setListLessons(songs));
        viewModel.getListLessonSongs().observe(this, songs->songListAdapter.setListSongs(songs));
        lessonListAdapter.setOnItemLessonClickListener(this::loadLesson);
        songListAdapter.setOnItemLessonClickListener(new SongListAdapter.OnItemClickListener() {
            @Override
            public void onItemDownload(int id) {
                downloadLesson(id);
            }

            @Override
            public void onItemLike(int id) {
                like(id);
            }

            @Override
            public void onItemLesson(int id) { showLessonActivityButtonClicked(id);

            }
        });
    }

    private void like(int i) {
        Song tempSong = songListAdapter.getSong(i);
        LikedSong song = new LikedSong();
        song.remoteId = tempSong.songId;
        song.songName = tempSong.songName;
        song.songId = tempSong.songId;
        localViewModel.insertLikedSong(song);
    }
    private void showLessonActivityButtonClicked(int id) {
        //viewModel.loadSongById(id);
        localViewModel.updateCurrentSong(songListAdapter.getSong(id));
        // create an alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(this));
        // builder.setTitle("Song id: " + id);
        // set the custom layout
       final View customLayout = getLayoutInflater().inflate(R.layout.dialog_listsong, null);
       builder.setView(customLayout);
        // add a button

       Button button_back = customLayout.findViewById(R.id.btn_back);
        ImageButton listen =  customLayout.findViewById(R.id.card_view_listen);
        ImageButton play =  customLayout.findViewById(R.id.card_view_play);

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
        listen.setOnClickListener(v -> PlayMusicWithInstructionActivity.startActivity(this, id));
        play.setOnClickListener(v -> InstructionActivity.startActivity(this, id));

        button_back.setOnClickListener(v -> dialog.cancel());
    }
    private void downloadLesson(int i) {
        localViewModel.insertSong(songListAdapter.getSong(i));
    }




    void loadLesson(String lesson){
        viewModel.loadListLessonById(lesson);
        recyclerView.setAdapter(songListAdapter);
    }
}