package com.example.pianoforkid.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.pianoforkid.R;

public class DetailLessonListActivity extends AppCompatActivity {
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, DetailLessonListActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_lesson_list);
    }
}