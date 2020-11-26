package com.example.pianoforkid.view.adaper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.pianoforkid.view.fragment.LessonListFragment;
import com.example.pianoforkid.view.fragment.LikedListFragment;
import com.example.pianoforkid.view.fragment.OnlineListFragment;
import com.example.pianoforkid.view.fragment.SavedListFragment;

public class PagerAdapter extends FragmentPagerAdapter {
    private int numOfTabs;
    public PagerAdapter(@NonNull FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs= numOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new LessonListFragment();
            case 1:
                return new LikedListFragment();
            case 2:
                return new SavedListFragment();
            case 3:
                return new OnlineListFragment();

        }
        return null;
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
