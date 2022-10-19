package com.example.gymapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class RegistrationActivity extends AppCompatActivity{

    private EditText edRegName, edRegPassword, edRegCardNum, edRegPhone;
    private String USER_KEY = "User";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        init();

    }

    public void onClickClose(View view) {
        Intent i = new Intent(RegistrationActivity.this, LogRegActivity.class);
        startActivity(i);
    }


    public void init(){
        edRegName = findViewById(R.id.edRegName);
        edRegPassword = findViewById(R.id.edRegPassword);
        edRegCardNum = findViewById(R.id.edRegCardNum);
        edRegPhone = findViewById(R.id.edRegPhone);
        mAuth = FirebaseAuth.getInstance();
    }


    public void onClickRegister(View view) {
        String name = edRegName.getText().toString();
        String password = edRegPassword.getText().toString();
        String cardNum = edRegCardNum.getText().toString();
        String phone = edRegPhone.getText().toString();

        //myDataBase.push().setValue(newUser);
        User newUser = new User(name, password, cardNum, phone);


        //Для пустых полей
        if (!TextUtils.isEmpty(edRegName.getText().toString()) &&
                !TextUtils.isEmpty(edRegPassword.getText().toString()) &&
                    !TextUtils.isEmpty(edRegCardNum.getText().toString()) &&
                        !TextUtils.isEmpty(edRegPhone.getText().toString())){

             mAuth.createUserWithEmailAndPassword(edRegName.getText().toString(), edRegPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        mAuth.signInWithEmailAndPassword(edRegName.getText().toString(), edRegPassword.getText().toString());
                        FirebaseDatabase.getInstance().getReference("User")
                                .child(mAuth.getCurrentUser().getUid())
                                .setValue(newUser);
                        Toast.makeText(getApplicationContext(), "Successful registration", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Registration error", Toast.LENGTH_SHORT).show();
                    }
                }
            });


            Intent i = new Intent(RegistrationActivity.this, LoginActivity.class);
            startActivity(i);
        }
        else{
            Toast.makeText(getApplicationContext(), "Fill in all the fields", Toast.LENGTH_SHORT).show();
        }

    }

}
