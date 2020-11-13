package com.example.pianoforkid.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pianoforkid.R;
import com.example.pianoforkid.view.adaper.SongListAdapter;
import com.example.pianoforkid.viewmodel.SongViewModel;

public class SongListActivity extends AppCompatActivity {

    private SongListAdapter songListAdapter;
    SongViewModel viewModel;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, SongListActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set view layout for this activity
        setContentView(R.layout.activity_song_list);

        Log.d("SongListActivity", "onCreate");

        viewModel = new ViewModelProvider(this).get(SongViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        songListAdapter = new SongListAdapter();

        //viewModel.getListSongs().observe(this, songs -> songListAdapter.setListSongs(songs));

        recyclerView.setAdapter(songListAdapter);

        songListAdapter.setOnItemLessonClickListener(new SongListAdapter.OnItemClickListener() {
            @Override
            public void onItemDownload(int id) {

            }

            @Override
            public void onItemLike(int id) {

            }

            @Override
            public void onItemLesson(int id) {

            }
        });
    }

    public void showAlertDialogButtonClicked(int id) {
        viewModel.loadSongById(id);
        // create an alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Song id: " + id);
        // set the custom layout
        final View customLayout = getLayoutInflater().inflate(R.layout.dialog_listsong, null);
        builder.setView(customLayout);
        // add a button

        ImageButton button_back = customLayout.findViewById(R.id.btn_back);
        ImageButton listen =  customLayout.findViewById(R.id.card_view_listen);
        ImageButton play =  customLayout.findViewById(R.id.card_view_play);

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
        listen.setOnClickListener(v -> PlayMusicWithInstructionActivity.startActivity(this, id));
        play.setOnClickListener(v -> InstructionActivity.startActivity(this, id));

        button_back.setOnClickListener(v -> dialog.cancel());
    }


}