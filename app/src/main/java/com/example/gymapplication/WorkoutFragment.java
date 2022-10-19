package com.example.gymapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class WorkoutFragment extends Fragment {


    private ArrayList<Coaches> coachesArrayList;
    private RecyclerView recycleview;
    private RecyclerAdapter.RecyclerViewClickListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        return inflater.inflate(R.layout.fragment_workout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        coachesArrayList = new ArrayList<>();
        readData(new FireBaseCallback() {
            @Override
            public void onCallback(ArrayList<Coaches> coaches) {
                setOnClickListener();
                recycleview = view.findViewById(R.id.recyclerview);
                recycleview.setLayoutManager(new LinearLayoutManager(getContext()));
                recycleview.setHasFixedSize(true);
                RecyclerAdapter myAdapter = new RecyclerAdapter(getContext(), coachesArrayList, listener);
                recycleview.setAdapter(myAdapter);
                myAdapter.notifyDataSetChanged();
            }
        });
    }

    private void setOnClickListener(){
        listener = new RecyclerAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getContext(), CoachActivity.class);
                intent.putExtra("name", coachesArrayList.get(position).getName());
                intent.putExtra("age", coachesArrayList.get(position).getAge());
                intent.putExtra("bio", coachesArrayList.get(position).getBio());
                intent.putExtra("avatar", coachesArrayList.get(position).getAvatar());
                startActivity(intent);

            }
        };
    }

    private void readData(FireBaseCallback fireBaseCallback){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(("Сoaches"));
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot sNapshot) {

                for(DataSnapshot snapshot : sNapshot.getChildren()){
                    Coaches coaches = new Coaches();
                    coaches.setName(snapshot.child("name").getValue().toString());
                    coaches.setAge(snapshot.child("age").getValue().toString());
                    coaches.setBio(snapshot.child("bio").getValue().toString());
                    coaches.setAvatar(snapshot.child("avatar").getValue().toString());

                    coachesArrayList.add(coaches);
                }
                fireBaseCallback.onCallback(coachesArrayList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private interface FireBaseCallback{
        void onCallback (ArrayList<Coaches> coaches);
    }

}

