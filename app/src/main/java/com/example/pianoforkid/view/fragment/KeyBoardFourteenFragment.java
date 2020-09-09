package com.example.pianoforkid.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.pianoforkid.R;
import com.example.pianoforkid.data.key.KeyNote;
import com.example.pianoforkid.data.model.Sound;
import com.example.pianoforkid.viewmodel.SongViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class KeyBoardFourteenFragment extends Fragment {

    int position;
    int lastNote;
    List<Sound> song;
    List<Sound> songTemp;
    SongViewModel viewModel;
    ImageButton btn1;
    ImageButton btn2;
    ImageButton btn3;
    ImageButton btn4;
    ImageButton btn5;
    ImageButton btn6;
    ImageButton btn7;
    ImageButton btn8;
    ImageButton btn9;
    ImageButton btn10;
    ImageButton btn11;
    ImageButton btn12;
    ImageButton btn13;
    ImageButton btn14;

    int songId;
    int songSize;
    int fault;
    int sizeTemp;

    int songLeft =0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_key_board_fourthteen, container, false);

        btn1 = view.findViewById(R.id.ibC1);
        btn2 = view.findViewById(R.id.ibD1);
        btn3 = view.findViewById(R.id.ibE1);
        btn4 = view.findViewById(R.id.ibF1);
        btn5 = view.findViewById(R.id.ibG1);
        btn6 = view.findViewById(R.id.ibA1);
        btn7 = view.findViewById(R.id.ibB1);
        btn8 = view.findViewById(R.id.ibC2);
        btn9 = view.findViewById(R.id.ibD2);
        btn10 = view.findViewById(R.id.ibE2);
        btn11 = view.findViewById(R.id.ibF2);
        btn12 = view.findViewById(R.id.ibG2);
        btn13 = view.findViewById(R.id.ibA2);
        btn14 = view.findViewById(R.id.ibB2);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.d("FourteenFragment", "onCreate");


        fault = 0;
        songSize = 0;
        songId = 1;
        sizeTemp = 0;
        //songId = Objects.requireNonNull(Objects.requireNonNull(getActivity()).getIntent().getExtras()).getInt("songId");
        Log.d("TAG Song", String.valueOf(songId));
        song = new ArrayList<>();
        songTemp = new ArrayList<>();

        lastNote = 0;

        viewModel = new ViewModelProvider(this).get(SongViewModel.class);
        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(Objects.requireNonNull(this.getActivity()).getApplication())).get(SongViewModel.class);


        viewModel.loadSongById(songId);
        viewModel.getCurrentSong().observe(getViewLifecycleOwner(), soundList -> {
            if (soundList.size() > 0) {
                song.addAll(soundList);
                songTemp.addAll(song);
                Log.d("TAG", String.valueOf(song.size()));
                Log.d("TAG", String.valueOf(song));
                songSize = song.size();
                songLeft = songSize;
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
    }

    public void check(View view) {
        int currentId = getCurrentId(view);

        final KeyNote keyNote = new KeyNote(Objects.requireNonNull(this.getActivity()).getApplication());
        keyNote.playNote(currentId);
    }


    int getCurrentId(View view) {
        switch (view.getId()) {
            case R.id.ibC1:
                Log.d("You chose", "btnC1");
                return 1;
            case R.id.ibD1:
                Log.d("You chose", "btnD1");
                return 2;
            case R.id.ibE1:
                Log.d("You chose", "btnE1");
                return 3;
            case R.id.ibF1:
                Log.d("You chose", "btnF1");
                return 4;
            case R.id.ibG1:
                Log.d("You chose", "btnG1");
                return 5;
            case R.id.ibA1:
                Log.d("You chose", "btnA1");
                return 6;
            case R.id.ibB1:
                Log.d("You chose", "btnB1");
                return 7;
            case R.id.ibC2:
                Log.d("You chose", "btnC2");
                return 8;
            case R.id.ibD2:
                Log.d("You chose", "btnD2");
                return 9;
            case R.id.ibE2:
                Log.d("You chose", "btnE2");
                return 10;
            case R.id.ibF2:
                Log.d("You chose", "btnF2");
                return 11;
            case R.id.ibG2:
                Log.d("You chose", "btnG2");
                return 12;
            case R.id.ibA2:
                Log.d("You chose", "btnA2");
                return 13;
            case R.id.ibB2:
                Log.d("You chose", "btnB2");
                return 14;
        }
        return 1;
    }

}