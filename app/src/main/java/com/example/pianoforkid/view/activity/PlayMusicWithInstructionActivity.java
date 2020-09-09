package com.example.pianoforkid.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pianoforkid.R;

public class PlayMusicWithInstructionActivity extends AppCompatActivity {

    public static void startActivity(Context context){
        Intent intent = new Intent(context, PlayMusicWithInstructionActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);

    }


}
