package com.example.gymapplication;

public class User {
    public String email, password, name, phone, workout;

    public User() {
    }

    public User(String email, String password, String name, String phone) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.workout = "No workouts";
    }
}
