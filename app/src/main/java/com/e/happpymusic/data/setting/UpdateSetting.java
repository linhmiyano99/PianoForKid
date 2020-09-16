package com.e.happpymusic.data.setting;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

public class UpdateSetting extends AsyncTask<Context, Void, Void> {

    private String key;
    private Boolean value;

    UpdateSetting(String key, Boolean value) {
        this.key = key;
        this.value = value;
    }

    @Override
    protected Void doInBackground(Context... contexts) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(contexts[0]);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
        Log.d("key, value", key + " " + value);
        return null;
    }
}
