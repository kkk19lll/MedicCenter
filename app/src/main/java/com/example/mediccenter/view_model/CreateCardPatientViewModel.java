package com.example.mediccenter.view_model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mediccenter.activities.CreatePatientCardActivity;
import com.example.mediccenter.api.API;
import com.example.mediccenter.api.ApiInstance;
import com.example.mediccenter.models.CreateCardPaitentModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateCardPatientViewModel extends ViewModel {
    private MutableLiveData<CreateCardPaitentModel> createUserData = new MutableLiveData<>();

    public MutableLiveData<CreateCardPaitentModel> getCreateUserData() {
        return createUserData;
    }

    public void createUser(CreateCardPaitentModel createCardPaitentModel) {
        API api = ApiInstance.getRetrofitInstance().create(API.class);
        Call<CreateCardPaitentModel> call = api.createCardPatient(createCardPaitentModel);

        call.enqueue(new Callback<CreateCardPaitentModel>() {
            @Override
            public void onResponse(Call<CreateCardPaitentModel> call, Response<CreateCardPaitentModel> response) {
                if (response.isSuccessful()) {
                    createUserData.postValue(response.body());
                }
                else {
                    createUserData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<CreateCardPaitentModel> call, Throwable t) {
                createUserData.postValue(null);
            }
        });
    }
}

