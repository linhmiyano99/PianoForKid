package com.example.pianoforkid.service;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pianoforkid.data.model.Song;
import com.example.pianoforkid.data.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class FirebaseService {
    private static  FirebaseService INSTANCE ;
    private MutableLiveData<List<User>> leaderBoard;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    public static FirebaseService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FirebaseService();
        }
        return INSTANCE;
    }

    private FirebaseService() {
        leaderBoard = new MutableLiveData<>();
        List<User> list= new ArrayList<>();
        list.add(new User(null, "Linh", null, 300));
        list.add(new User(null, "Satoh", null, 100));
        leaderBoard.setValue(list);
        firebaseDatabase = FirebaseDatabase.getInstance();
    }

    public void loadLeaderBoard() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        databaseReference = firebaseDatabase.getReference("user");

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
}
