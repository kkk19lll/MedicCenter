package com.example.mediccenter.view_model;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mediccenter.api.API;
import com.example.mediccenter.api.ApiInstance;
import com.example.mediccenter.models.UserAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthViewModel extends ViewModel {
    private final MutableLiveData<UserAuth> authUser = new MutableLiveData<>();

    public MutableLiveData<UserAuth> getAuthUser() {
        return authUser;
    }

    public void authUser(UserAuth userAuth) {
        API api = ApiInstance.getRetrofitInstance().create(API.class);
        Call<UserAuth> call = api.createPost(userAuth);
        call.enqueue(new Callback<UserAuth>() {
            @Override
            public void onResponse(@NonNull Call<UserAuth> call, @NonNull Response<UserAuth> response) {
                if (response.isSuccessful()) {
                    authUser.postValue(response.body());
                }
                else {
                    authUser.postValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserAuth> call, @NonNull Throwable t) {
                authUser.postValue(null);
            }
        });
    }
}
