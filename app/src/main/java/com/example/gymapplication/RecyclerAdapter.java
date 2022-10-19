package com.example.gymapplication;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{

    private Context context;
    private ArrayList<Coaches> coachesArrayList;
    private static RecyclerViewClickListener listener;

    public RecyclerAdapter(Context context, ArrayList<Coaches> coachesArrayList, RecyclerViewClickListener listener){
        this.context = context;
        this.coachesArrayList = coachesArrayList;
        this.listener = listener;

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView;
        TextView txtName;
        TextView txtAge;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ImageViewToTest);
            txtName = itemView.findViewById(R.id.twName);
            txtAge = itemView.findViewById(R.id.twAge);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }


    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.workout_item, parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        Coaches coaches = coachesArrayList.get(position);
        holder.txtAge.setText(coaches.age);
        holder.txtName.setText(coaches.name);
        Glide.with(context).load(coachesArrayList.get(position).getAvatar()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return coachesArrayList.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }
}