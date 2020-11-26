package com.example.pianoforkid.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.pianoforkid.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.example.pianoforkid.view.adaper.PagerAdapter;

public class AllListActivity extends AppCompatActivity {
    TabLayout tabLayout;
    TabItem tab_lesson, tab_like, tab_saved, tab_online;
    ViewPager viewPager;
    public static void startActivity(Context context){
        Intent intent = new Intent(context, AllListActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_list);
        tabLayout= findViewById(R.id.tab_layout);
        tab_lesson= findViewById(R.id.tab_item_lesson);
        tab_like= findViewById(R.id.tab_item_like);
        tab_saved= findViewById(R.id.tab_item_saved);
        tab_online= findViewById(R.id.tab_item_online);
        viewPager= findViewById(R.id.fragment_container);

        PagerAdapter pagerAdapter= new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}