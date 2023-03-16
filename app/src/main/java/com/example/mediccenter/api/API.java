package com.example.mediccenter.api;

import com.example.mediccenter.models.UserAuth;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface API {
    @FormUrlEncoded
    @POST("/api/signin")
    Call<UserAuth> createPost(@Field("email") String email);
}
