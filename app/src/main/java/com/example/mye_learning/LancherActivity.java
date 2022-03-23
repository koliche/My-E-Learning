package com.example.mye_learning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LancherActivity extends AppCompatActivity {
    Button get_start ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancher);

        get_start = findViewById(R.id.startBTN);

        get_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LancherActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}