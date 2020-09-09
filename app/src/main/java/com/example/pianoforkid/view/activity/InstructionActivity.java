package com.example.pianoforkid.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pianoforkid.R;

public class InstructionActivity extends AppCompatActivity {

    public static void startActivity(Context context){
        Intent intent = new Intent(context, InstructionActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);

    }


}
