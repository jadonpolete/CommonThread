package com.example.commonthread.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.commonthread.repository.AuthRepository;

/*
 * LoginViewModel handles login logic and communicates with the Repository.
 * The View observes LiveData from this class to update the UI.
 */
public class LoginViewModel extends ViewModel {

    // Repository instance
    private final AuthRepository repository = new AuthRepository();

    // LiveData to store login result
    private final MutableLiveData<String> loginResult = new MutableLiveData<>();

    // Expose LiveData to View
    public LiveData<String> getLoginResult() {
        return loginResult;
    }

    /*
     * Processes login request from View
     */
    public void login(String email, String password) {

        // Validate empty fields
        if (email.isEmpty() || password.isEmpty()) {
            loginResult.setValue("Please fill in all fields.");
        }

        // Check credentials using repository
        else if (repository.login(email, password)) {
            loginResult.setValue("Login successful. Welcome to CommonThread.");
        }

        // Invalid login
        else {
            loginResult.setValue("Invalid email or password.");
        }
    }
}