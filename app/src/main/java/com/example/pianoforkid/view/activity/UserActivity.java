package com.example.pianoforkid.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.pianoforkid.R;
import com.example.pianoforkid.data.model.User;
import com.example.pianoforkid.viewmodel.FirebaseViewModel;
import com.example.pianoforkid.viewmodel.UserViewModel;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.Objects;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {
    //*****************
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private String userId;
    User userX;
    TextView txt_email;
    TextView txt_user_name;
    TextView txt_score;
    Button btn_sign_out;
    ImageButton btn_back;

    // Choose an arbitrary request code value
    private static final int RC_SIGN_IN = 1;
    //*****************
    UserViewModel userViewModel;
    FirebaseViewModel firebaseViewModel;
    public static void startActivity(Context context){
        Intent intent = new Intent(context, UserActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        firebaseViewModel = new ViewModelProvider(this).get(FirebaseViewModel.class);
        txt_email = findViewById(R.id.txt_email);
        txt_user_name = findViewById(R.id.txt_user_name);
        txt_score = findViewById(R.id.txt_score);
        btn_sign_out = findViewById(R.id.btn_sign_out);
        btn_back = findViewById(R.id.btn_back);


        //*****************
        mFirebaseAuth = FirebaseAuth.getInstance();
        //*****************

        mAuthStateListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if(user != null){
                //sign in
                userViewModel.getUser().observe(this, u ->
                {
                    userX = u;
                    try {
                        Log.d("mAuthStateListener", String.valueOf(u));
                        txt_email.setText(u.email);
                        txt_user_name.setText(u.name);
                        txt_score.setText(String.valueOf(u.score));
                        userId = u.userId;
                        Log.d("mAuthStateListener2", "mAuthStateListener2");
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                });
            }else{
                //sign out
                btn_sign_out.setText(R.string.sign_in);

            }
        };
        //*****************
        btn_sign_out.setOnClickListener(this);
        btn_back.setOnClickListener(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d("UserActivity", "onResume");
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            //IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    Log.d("xxuser", Objects.requireNonNull(user.getUid()));
                    userViewModel.insertUser(user);
                }
                // ...
                btn_sign_out.setText(R.string.sign_out);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sign_out:
                if(btn_sign_out.getText().toString().equals("Sign out")) {
                    AuthUI.getInstance()
                            .signOut(this)
                            .addOnCompleteListener(task -> {
                                txt_email.setText("");
                                txt_user_name.setText("");
                                txt_score.setText("");
                                userViewModel.delete(userId);
                            });
                }
                else{
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setAvailableProviders(Arrays.asList(
                                            new AuthUI.IdpConfig.GoogleBuilder().build(),
                                            new AuthUI.IdpConfig.FacebookBuilder().build(),
                                            new AuthUI.IdpConfig.AnonymousBuilder().build()))
                                    .setTheme(R.style.FirebaseLoginTheme)
                                    .build(),

                            RC_SIGN_IN);
                }
                break;
            case R.id.btn_back:
                onBackPressed();
                break;
        }
    }

}