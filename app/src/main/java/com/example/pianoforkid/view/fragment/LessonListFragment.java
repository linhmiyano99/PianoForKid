package com.example.pianoforkid.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pianoforkid.R;
import com.example.pianoforkid.data.model.Song;
import com.example.pianoforkid.view.adaper.LessonListAdapter;
import com.example.pianoforkid.viewmodel.FirebaseViewModel;
import com.example.pianoforkid.viewmodel.SongViewModel;


public class LessonListFragment extends Fragment {
    public LessonListAdapter lessonListAdapter;
    FirebaseViewModel viewModel;
    SongViewModel localViewmodel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_lesson_list, container, false);
        viewModel = new ViewModelProvider(this).get(FirebaseViewModel.class);
        localViewmodel = new ViewModelProvider(this).get(SongViewModel.class);


        RecyclerView recyclerView= view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        lessonListAdapter = new LessonListAdapter();
        recyclerView.setAdapter(lessonListAdapter);
        viewModel.loadAllSongs();
        viewModel.getListSongs().observe(getViewLifecycleOwner(), songs->lessonListAdapter.setListSongs(songs));
        lessonListAdapter.setOnItemLessonClickListener(this::showLessonActivityButtonClicked);
        lessonListAdapter.setOnItemLessonDownloadClickListener(this::downloadLesson);
        return view;
    }

    private void downloadLesson(int i) {
        Song s = lessonListAdapter.getSong(i);
        localViewmodel.insertSong(s);
    }

    private void showLessonActivityButtonClicked(int i) {

    }

}