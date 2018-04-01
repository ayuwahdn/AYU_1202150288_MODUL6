package com.example.android.ayu_1202150288_studycase6.Model;

public class User {

    String userId;
    String username;
    String email;

    public User() {
    }

    public User(String userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
    }

    public String getUserId() {

        return userId;
    }

    public String getUsername() {

        return username;
    }

    public String getEmail() {

        return email;
    }
}
