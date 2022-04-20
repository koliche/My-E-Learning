
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.mye_learning.classes.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    EditText signFullname;
    EditText signEmail;
    EditText signPassword;
    TextView signConfPassword;
    Button registerBtn ;
    TextView signLogin;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signFullname = findViewById(R.id.signFullname);
        signEmail = findViewById(R.id.signEmail);
        signPassword = findViewById(R.id.signPassword);
        signConfPassword = findViewById(R.id.signConfPassword);
        registerBtn = findViewById(R.id.registerBTN);
        signLogin = findViewById(R.id.signLogin);
        progressBar = findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();

        signLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }

            private void registerUser() {
                String fullname = signFullname.getText().toString().trim();
                String email = signEmail.getText().toString().trim();
                String password = signPassword.getText().toString().trim();
                String cofirmePassword = signConfPassword.getText().toString().trim();

                if (fullname.isEmpty()){
                    signFullname.setError("Full name is required!");
                    signFullname.requestFocus();
                    return;
                }
                if (email.isEmpty()){
                    signEmail.setError("Email is required!");
                    signEmail.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    signEmail.setError("Please provide valid Email");
                    signEmail.requestFocus();
                    return;
                }
                if (password.isEmpty()){
                    signPassword.setError("Password is required!");
                    signPassword.requestFocus();
                    return;
                }
                if (password.length() < 6){
                    signPassword.setError("Min Password length should be 6 character");
                    signPassword.requestFocus();
                    return;
                }
                if (cofirmePassword.isEmpty()){
                    signConfPassword.setError("Password is required!");
                    signConfPassword.requestFocus();
                    return;
                }
                if (!cofirmePassword.equals(password)){
                    signConfPassword.setError("Password is required!");
                    signConfPassword.requestFocus();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(email , password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            User user = new User(fullname, email);
                            FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(SignupActivity.this, "User Has been register successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
                                        startActivity(intent);
                                        finish();

                                    }
                                    else {
                                        Toast.makeText(SignupActivity.this, "Failed to register try again", Toast.LENGTH_SHORT).show();
                                    }
                                    progressBar.setVisibility(View.GONE);
                                }
                            });
                        }
                        else {
                            Toast.makeText(SignupActivity.this, "Failed to register try again", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });


    }
}