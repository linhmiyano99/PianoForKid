package com.example.pianoforkid.service;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pianoforkid.data.localdatabase.AppRoomDatabase;
import com.example.pianoforkid.data.model.Song;
import com.example.pianoforkid.data.model.User;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseService {
    private static  FirebaseService INSTANCE ;
    private MutableLiveData<List<User>> leaderBoard;
    private MutableLiveData<List<Song>> songList;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private Application application;

    public static FirebaseService getInstance(Application application) {
        if (INSTANCE == null) {
            INSTANCE = new FirebaseService(application);
        }
        return INSTANCE;
    }

    private FirebaseService(Application application) {
        leaderBoard = new MutableLiveData<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        songList = new MutableLiveData<>();
        this.application = application;
    }

    public void loadLeaderBoard() {
        Log.e("Count ", "loadLeaderBoard");

        databaseReference = firebaseDatabase.getReference("leaderboard");

        databaseReference.orderByChild("score")
                .addValueEventListener(new ValueEventListener() {
                                         @Override
                                         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                             Log.e("Count ", "" + dataSnapshot.getChildrenCount());
                                             List<User> list = new ArrayList<>();
                                             for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                                                 User post = postSnapshot.getValue(User.class);
                                                 list.add(post);
                                                 // properties of song
                                             }
                                             leaderBoard.setValue(list);
                                         }

                                         @Override
                                         public void onCancelled(@NonNull DatabaseError databaseError) {
                                             Log.e("The read failed: ", databaseError.getMessage());

                                         }
                                     }
                );
    }

    public LiveData<List<User>> getUserList(){
        return leaderBoard;
    }

    public void loadAllSongs() {
        databaseReference = firebaseDatabase.getReference("song_table");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("Count ", "" + dataSnapshot.getChildrenCount());
                List<Song> list = new ArrayList<>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Song post = postSnapshot.getValue(Song.class);
                    list.add(post);

                    // properties of song
                }
                songList.setValue(list);
                Log.e("Count ", String.valueOf(songList));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("The read failed: ", databaseError.getMessage());

            }
        });

    }

    public void createUser(FirebaseUser fUser){
        User player = new User();
        databaseReference = firebaseDatabase.getReference("leaderboard").child(fUser.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getChildrenCount() == 0) {
                    player.userId = fUser.getUid();
                    player.name = fUser.getDisplayName();
                    player.email = fUser.getEmail();
                    player.score = 0;
                    databaseReference.setValue(player);
                }
                AppRoomDatabase.databaseWriteExecutor.execute(() ->AppRoomDatabase.getDatabase(application).userDao().insert(player));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("The read failed: ", databaseError.getMessage());

            }
        });
    }

    public void addScore(int score, User user) {
        user.score += score;
        firebaseDatabase.getReference("leaderboard")
                .child(user.userId)
                .child("score")
                .setValue(user.score);
        firebaseDatabase.getReference("leaderboard")
                .child(user.userId)
                .child("name")
                .setValue(user.name);
        AppRoomDatabase.databaseWriteExecutor
                .execute(() -> AppRoomDatabase.getDatabase(application).userDao()
                .updateScore(user.userId, user.score));

        Log.e("score ", String.valueOf(user.score + score));
    }

    public LiveData<List<Song>> getListSongs() {
        return songList;
    }

    public User getUser(String uid){
        final User[] user = {new User()};
        databaseReference = firebaseDatabase.getReference("leaderboard").child(uid);

        databaseReference
                .addValueEventListener(new ValueEventListener() {
                                           @Override
                                           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                               Log.e("Count ", "" + dataSnapshot.getChildrenCount());
                                               List<User> list = new ArrayList<>();
                                               for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                                                   try {
                                                       user[0] = postSnapshot.getValue(User.class);
                                                   }
                                                   catch (Exception e){
                                                       user[0] = null;
                                                   }
                                               }
                                               leaderBoard.setValue(list);
                                           }

                                           @Override
                                           public void onCancelled(@NonNull DatabaseError databaseError) {
                                               Log.e("The read failed: ", databaseError.getMessage());

                                           }
                                       }
                );
        return user[0];
    }

}
