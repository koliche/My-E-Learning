package com.example.mye_learning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {

    EditText emailtext;
    Button restBtn;
    ProgressBar progressBar;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        emailtext = findViewById(R.id.resetEmail);
        restBtn = findViewById(R.id.resetBTN);
        progressBar = findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();

        restBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }

            private void resetPassword() {
                String email = emailtext.getText().toString().trim();

                if (email.isEmpty()){
                    emailtext.setError("Email is required!");
                    emailtext.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    emailtext.setError("Please provide valid Email");
                    emailtext.requestFocus();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(ResetPasswordActivity.this, "Check your email to reset your password", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ResetPasswordActivity.this,LoginActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(ResetPasswordActivity.this, "Try again !", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}