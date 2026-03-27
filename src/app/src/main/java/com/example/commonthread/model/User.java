package com.example.commonthread.model;

/*
 * User model class represents user data.
 */
public class User {

    private String email;
    private String password;

    // Constructor
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getter for email
    public String getEmail() {
        return email;
    }

    // Getter for password
    public String getPassword() {
        return password;
    }
}