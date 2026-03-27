package com.example.commonthread.repository;

/*
 * AuthRepository handles authentication logic.
 * This class simulates checking login credentials.
 */
public class AuthRepository {

    /*
     * Simulated login method
     */
    public boolean login(String email, String password) {

        // Hardcoded credentials for demonstration
        return email.equals("user@commonthread.com") && password.equals("123456");
    }
}