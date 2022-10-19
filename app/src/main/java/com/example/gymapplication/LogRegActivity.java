package com.example.gymapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LogRegActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logreg);
    }

    public void onClickSignIn(View view){
        Intent i = new Intent(LogRegActivity.this, LoginActivity.class);
        startActivity(i);
    }

    public void onClickSignUp(View view){
        Intent i = new Intent(LogRegActivity.this, RegistrationActivity.class);
        startActivity(i);

    }
}
