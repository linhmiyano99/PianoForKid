package com.example.pianoforkid.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.pianoforkid.R;
import com.example.pianoforkid.view.activity.InstructionActivity;
import com.example.pianoforkid.view.activity.PlayMusicWithInstructionActivity;
import com.example.pianoforkid.view.adaper.SongListAdapter;
import com.example.pianoforkid.viewmodel.SongViewModel;

import java.util.Objects;


public class SavedListFragment extends Fragment {
    SongListAdapter songListAdapter;
    SongViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_saved_list, container, false);
        RecyclerView recyclerView= view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        songListAdapter = new SongListAdapter();
        recyclerView.setAdapter(songListAdapter);
        songListAdapter.setOnItemSongClickListener(this::showAlertDialogButtonClicked);

        return view;
    }

    private void showAlertDialogButtonClicked(int id) {
        //viewModel.loadSongById(id);
        // create an alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
       // builder.setTitle("Song id: " + id);
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
        listen.setOnClickListener(v -> PlayMusicWithInstructionActivity.startActivity(getActivity(), id));
        play.setOnClickListener(v -> InstructionActivity.startActivity(getActivity(), id));

        button_back.setOnClickListener(v -> dialog.cancel());
    }
    }
