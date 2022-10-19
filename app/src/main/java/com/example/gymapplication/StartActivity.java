package com.example.gymapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;


public class StartActivity extends AppCompatActivity{

    private ProgressBar pb;
    private int counter = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //progressbar
        pb = findViewById(R.id.progBar);
        final Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                counter ++;
                pb.setProgress(counter);

                if (counter == 5000)
                    t.cancel();
            }
        };

        t.schedule(tt, 0, 5000);


        //supportActionBar?.hide();
        new Handler().postDelayed(new Runnable() {
              @Override
              public void run() {
                  Intent i = new Intent(StartActivity.this, LogRegActivity.class);
                  startActivity(i);
                  finish();
              }
        }, 5000);


    }

}