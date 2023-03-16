package com.example.mediccenter.models;

import com.google.gson.annotations.SerializedName;

public class UserAuth {
    @SerializedName("email")
    String email;

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
