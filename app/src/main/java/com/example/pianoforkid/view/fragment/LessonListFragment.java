package com.example.pianoforkid.view.fragment;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.pianoforkid.R;
import com.example.pianoforkid.data.model.LikedSong;
import com.example.pianoforkid.data.model.Song;
import com.example.pianoforkid.view.activity.InstructionActivity;
import com.example.pianoforkid.view.activity.PlayMusicWithInstructionActivity;
import com.example.pianoforkid.view.adaper.LessonListAdapter;
import com.example.pianoforkid.viewmodel.FirebaseViewModel;
import com.example.pianoforkid.viewmodel.SongViewModel;


public class LessonListFragment extends Fragment {
    public LessonListAdapter lessonListAdapter;
    FirebaseViewModel viewModel;
    SongViewModel localViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_lesson_list, container, false);
        viewModel = new ViewModelProvider(this).get(FirebaseViewModel.class);
        localViewModel = new ViewModelProvider(this).get(SongViewModel.class);


        RecyclerView recyclerView= view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        lessonListAdapter = new LessonListAdapter();
        recyclerView.setAdapter(lessonListAdapter);
        viewModel.loadAllSongs();
        viewModel.getListSongs().observe(getViewLifecycleOwner(), songs->lessonListAdapter.setListSongs(songs));
        lessonListAdapter.setOnItemLessonClickListener(this::showLessonActivityButtonClicked);
        lessonListAdapter.setOnItemLessonDownloadClickListener(this::downloadLesson);
        lessonListAdapter.setOnItemLikeClickListener(this::like);
        return view;
    }

    private void like(int i) {
        Song tempSong = lessonListAdapter.getSong(i);
        LikedSong song = new LikedSong();
        song.remoteId = tempSong.songId;
        song.songName = tempSong.songName;
        song.songId = tempSong.songId;
        localViewModel.insertLikedSong(song);
    }

    private void downloadLesson(int i) {
        localViewModel.insertSong(lessonListAdapter.getSong(i));
    }

    private void showLessonActivityButtonClicked(int id) {
        //viewModel.loadSongById(id);
        localViewModel.updateCurrentSong(lessonListAdapter.getSong(id));
        // create an alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
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
        listen.setOnClickListener(v -> PlayMusicWithInstructionActivity.startActivity(getActivity(), id));
        play.setOnClickListener(v -> InstructionActivity.startActivity(getActivity(), id));

        button_back.setOnClickListener(v -> dialog.cancel());
    }

}