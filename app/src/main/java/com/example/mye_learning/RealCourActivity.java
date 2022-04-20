package com.example.mye_learning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RealCourActivity extends AppCompatActivity {
    String realCourKey ;
    String catKey;
    VideoView courVideo;
    TextView titleOfTheCour;
    TextView bodyOfTheCour;
    FirebaseDatabase database;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_cour);
        courVideo = findViewById(R.id.courVideo);
        titleOfTheCour = findViewById(R.id.titleOfTheCour);
        bodyOfTheCour = findViewById(R.id.bodyOfTheCour);
        catKey = getIntent().getStringExtra("catKey");
        realCourKey = getIntent().getStringExtra("coursKey");
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("Catigorie").child(catKey).child("allCours").child(realCourKey);
        MediaController mediaController = new MediaController(this);
        //Toast.makeText(this, realCourKey, Toast.LENGTH_SHORT).show();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String title = String.valueOf(snapshot.child("title").getValue());
                String body = String.valueOf(snapshot.child("body").getValue());
                titleOfTheCour.setText(title);
                bodyOfTheCour.setText(body);
                courVideo.setMediaController(mediaController);
                String videouri = String.valueOf(snapshot.child("video").getValue());
                Uri uri = Uri.parse(videouri);
                courVideo.setVideoURI(uri);
                courVideo.start();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}