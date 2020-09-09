package com.example.pianoforkid.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

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


public class InstructionFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_instruction, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("InstructionFragment", "onCreate");

    }



}