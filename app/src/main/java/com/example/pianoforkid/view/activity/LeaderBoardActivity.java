package com.example.pianoforkid.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.pianoforkid.R;
import com.example.pianoforkid.adaper.LeaderBoardAdapter;
import com.example.pianoforkid.viewmodel.FirebaseViewModel;

public class LeaderBoardActivity extends AppCompatActivity {
    TextView text_view_grade;

    FirebaseViewModel viewModel;
    private LeaderBoardAdapter leaderBoardAdapter;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, LeaderBoardActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set view layout for this activity
        setContentView(R.layout.activity_leader_board);

        Log.d("LeaderBoardActivity", "onCreate");


        viewModel = new ViewModelProvider(this).get(FirebaseViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        text_view_grade= findViewById(R.id.text_view_grade);

        leaderBoardAdapter = new LeaderBoardAdapter();
        viewModel.loadLeaderBoard();
        viewModel.getUserList().observe(this, users -> leaderBoardAdapter.setUserList(users));

        recyclerView.setAdapter(leaderBoardAdapter);
        leaderBoardAdapter.setOnItemSongClickListener(this::showUserDetail);
    }

    public void showUserDetail(int id) {
        /*// create an alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Song id: " + id);
        // set the custom layout
        final View customLayout = getLayoutInflater().inflate(R.layout.dialog_listsong, null);
        builder.setView(customLayout);
        // add a button

        ImageButton button_back = customLayout.findViewById(R.id.btn_back);
        CardView listen =  customLayout.findViewById(R.id.card_view_listen);
        CardView play =  customLayout.findViewById(R.id.card_view_play);

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
        listen.setOnClickListener(v -> PlayMusicWithInstructionActivity.startActivity(this, id));
        play.setOnClickListener(v -> InstructionActivity.startActivity(this, id));

        button_back.setOnClickListener(v -> dialog.cancel());*/
    }


}