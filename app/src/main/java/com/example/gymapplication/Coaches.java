package com.example.gymapplication;

public class Coaches {
    public String name, bio, age, avatar;

    public Coaches() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public String getAge() {
        return age;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Coaches(String bio, String name, String age, String avatar) {
        this.bio = bio;
        this.name = name;
        this.age = age;
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }
}
