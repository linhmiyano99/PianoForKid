package com.example.pianoforkid.view.fragment;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.pianoforkid.R;
import com.example.pianoforkid.view.activity.InstructionActivity;
import com.example.pianoforkid.view.activity.PlayMusicWithInstructionActivity;
import com.example.pianoforkid.view.adaper.LessonListAdapter;
import com.example.pianoforkid.view.adaper.SongListAdapter;
import com.example.pianoforkid.viewmodel.SongViewModel;

import java.util.Objects;


public class LessonListFragment extends Fragment {
    public LessonListAdapter lessonListAdapter;
    ViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_lesson_list, container, false);


        RecyclerView recyclerView= view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        lessonListAdapter = new LessonListAdapter();
        recyclerView.setAdapter(lessonListAdapter);
        lessonListAdapter.setOnItemLessonClickListener(this::showLessonActivityButtonClicked);
        return view;



    }

    private void showLessonActivityButtonClicked(int i) {

    }

}