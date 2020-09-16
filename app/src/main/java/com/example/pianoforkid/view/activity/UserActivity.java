package com.example.pianoforkid.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.example.pianoforkid.R;

public class UserActivity extends AppCompatActivity {

    public static void startActivity(Context context){
        Intent intent = new Intent(context, UserActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        ImageButton button_back = findViewById(R.id.btn_back);
        button_back.setOnClickListener(v -> onBackPressed());
    }
}