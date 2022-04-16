package com.example.mye_learning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LancherActivity extends AppCompatActivity {
    Button get_start ;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancher);
        mAuth = FirebaseAuth.getInstance();
        get_start = findViewById(R.id.startBTN);
        FirebaseUser currentUser = mAuth.getCurrentUser();
        get_start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(currentUser == null){
                    Intent intent = new Intent(LancherActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent = new Intent(LancherActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}