package com.example.mediccenter.models;

public class UserAuth {
    private String email;

    public UserAuth(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
