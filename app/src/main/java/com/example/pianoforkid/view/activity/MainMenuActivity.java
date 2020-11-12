package com.example.pianoforkid.view.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.pianoforkid.R;
import com.example.pianoforkid.viewmodel.SettingViewModel;
import com.example.pianoforkid.viewmodel.SongViewModel;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

public class MainMenuActivity extends AppCompatActivity implements View.OnClickListener {

    SongViewModel viewModel;
    SettingViewModel settingViewModel;
    CallbackManager callbackManager;
    ShareDialog shareDialog;
    ImageButton btn_share_facebook;
    ImageButton btn_rank;
    Button btn_user;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, MainMenuActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Log.d("MainMenuActivity", "onCreate");

        Button button_songs = findViewById(R.id.buttonListSong);
        Button button_instruments = findViewById(R.id.buttonInstrument);

        btn_share_facebook = findViewById(R.id.btn_share_facebook);
        btn_rank= findViewById(R.id.imageButtonRank);
        btn_user = findViewById(R.id.btn_user);
        ImageView button_settings = findViewById(R.id.imageButtonSetting);
        button_songs.setOnClickListener(this);
        button_instruments.setOnClickListener(this);
        btn_share_facebook.setOnClickListener(this);
        btn_rank.setOnClickListener(this);
        btn_user.setOnClickListener(this);

        button_settings.setOnClickListener(this);
        viewModel = new ViewModelProvider(this).get(SongViewModel.class);
        settingViewModel = new ViewModelProvider(this).get(SettingViewModel.class);

        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
        // this part is optional
        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {
                Log.d("MainMenuActivity", "onSuccess");

            }

            @Override
            public void onCancel() {
                Log.d("MainMenuActivity", "onCancel");

            }

            @Override
            public void onError(FacebookException error) {
                Log.d("MainMenuActivity", "onError");

            }
        });


        Log.d("MainMenuActivity", "shareDialog1");
    }
    @Override
    public void onClick(View v) {
        // Perform action on click
        Log.d("MainMenuActivity", "shareDialog2");

        switch (v.getId()) {
            case R.id.buttonListSong:
                viewModel.getListSongs().observe(this, songs ->
                {
                });
                AllListActivity.startActivity(this);
                break;
            case R.id.buttonInstrument:
                SaveMusicActivity.startActivity(this);
                break;
            case R.id.imageButtonSetting:
                SettingActivity.startActivity(this);
                break;
            case R.id.btn_share_facebook:

                    Log.d("MainMenuActivity", "shareDialog3");

                if (ShareDialog.canShow(ShareLinkContent.class)) {
                  /*  Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.audio);

                    SharePhoto photo = new SharePhoto.Builder()
                            .setBitmap(image)
                            .build();
                    SharePhotoContent content = new SharePhotoContent.Builder()
                            .addPhoto(photo)
                            .build();*/
                    ShareLinkContent content = new ShareLinkContent.Builder()
                            .setContentUrl(Uri.parse("https://developer.android.com/training/wearables"))
                            .setQuote("Connect on a global scale.")
                            .build();
                    shareDialog.show(content);
                }
                    break;
            case R.id.imageButtonRank:
                LeaderBoardActivity.startActivity(this);
                break;
            case R.id.btn_user:
                UserActivity.startActivity(this);
                break;
        }
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
