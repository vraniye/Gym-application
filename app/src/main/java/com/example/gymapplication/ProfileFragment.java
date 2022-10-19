package com.example.gymapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("User");
        userID = user.getUid();

        final TextView txtName = (TextView) view.findViewById(R.id.twUsername);
        final TextView txtEmail = (TextView) view.findViewById(R.id.textVEmail);
        final TextView txtPhone = (TextView) view.findViewById(R.id.twPhone);
        final TextView txtWorkouts = (TextView) view.findViewById(R.id.twProfileWorkouts);

        reference.child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                String name = userProfile.name;
                String email = userProfile.email;
                String phone = userProfile.phone;
                String workouts = userProfile.workout;

                txtPhone.setText(phone);
                txtEmail.setText(email);
                txtName.setText(name);
                txtWorkouts.setText(workouts);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        return view;
    }
}