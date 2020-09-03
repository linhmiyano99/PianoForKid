package com.example.pianoforkid.data.setting;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.ArrayMap;
import android.util.Log;

import java.util.Map;

public class LoadSettingTask extends AsyncTask<Context, Void, Map<String, Boolean>> {
    private ListSettingResponse delegate = null;

    void setDelegate(ListSettingResponse delegate) {
        this.delegate = delegate;
    }

    @Override
    protected Map<String, Boolean> doInBackground(Context... contexts) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(contexts[0]);
        Map<String, Boolean> listBookmark = new ArrayMap<>();
        if(!sharedPreferences.contains("language")){
            @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("language", false);
            editor.putBoolean("isSound", false);
            editor.putBoolean("isBackgroundMusic", false);
        }
        listBookmark.put("language", sharedPreferences.getBoolean("language", false));
        listBookmark.put("isSound", sharedPreferences.getBoolean("isSound", false));
        listBookmark.put("isBackgroundMusic",sharedPreferences.getBoolean("isBackgroundMusic",false));
        return listBookmark;
    }
    @Override
    protected void onPostExecute(Map<String, Boolean> result) {
        if(result != null){
            Log.d("onPostExecute", "yeah");
            delegate.processListSettingFinish(result);
        } else{
            Log.e("MyMessage", "Failed to fetch data!");
        }
    }
}
