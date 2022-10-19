package com.example.gymapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class CoachActivity extends AppCompatActivity {

    private DatePickerDialog.OnDateSetListener datePickerDialog;
    private Button chooseDate;
    private Button btnWorkout;
    private Button btnTime;
    private TextView txtDate;
    private TextView txtTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach);

        TextView nameCoach = findViewById(R.id.txtCoachName);
        TextView ageCoach = findViewById(R.id.txtCoachAge);
        TextView bioCoach = findViewById(R.id.txtCoachBio);
        ImageView imageV = findViewById(R.id.imageV);
        chooseDate = findViewById(R.id.btnDate);
        txtDate = findViewById(R.id.txtDate);
        btnWorkout = findViewById(R.id.btnZapis);
        btnTime = findViewById(R.id.btnTime);
        txtTime = findViewById(R.id.txtTime);

        String name = "Name not set";
        String age = "Age not set";
        String bio = "Bio not set";
        String avatar = "Avatar not set";

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            name = extras.getString("name");
            age = extras.getString("age");
            bio = extras.getString("bio");
            avatar = extras.getString("avatar");
        }

        nameCoach.setText(name);
        ageCoach.setText(age);
        bioCoach.setText(bio);
        Glide.with(this).load(avatar).into(imageV);

        chooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(calendar.YEAR);
                int month = calendar.get(calendar.MONTH);
                int day = calendar.get(calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(view.getContext(), android.R.style.Theme_DeviceDefault_Dialog, datePickerDialog, year, month, day);
                dialog.show();
            }
        });

        datePickerDialog = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date  = day + "/" + month + "/" + year;
                txtDate.setText(date);
            }
        };

        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(CoachActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        int hour = timePicker.getHour();
                        int minute = timePicker.getMinute();
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(0,0,0, hour, minute);
                        txtTime.setText(DateFormat.format("hh:mm", calendar));
                    }
                }, 24, 0, true
                );
                timePickerDialog.show();
            }
        });

        btnWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtDate.getText().toString().equals("--/--/--") || txtTime.getText().toString().equals("--:--")){
                    Toast.makeText(getApplicationContext(), "You must select a date and time", Toast.LENGTH_SHORT).show();
                }
                else{
                    String workout = "Time: " + txtTime.getText().toString() + '\n'
                            + "Date : " +  txtDate.getText().toString() + '\n'
                            + "Coach : " + nameCoach.getText().toString();

                    FirebaseDatabase.getInstance().getReference("User")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .child("workout").setValue(workout);
                    Toast.makeText(getApplicationContext(), "Sign up successful", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
