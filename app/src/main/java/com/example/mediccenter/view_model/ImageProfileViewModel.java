package com.example.mediccenter.view_model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mediccenter.api.API;
import com.example.mediccenter.api.ApiInstance;
import com.example.mediccenter.models.ImageProfileModel;
import com.example.mediccenter.models.UpdateCardPatientModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageProfileViewModel extends ViewModel {

    private final MutableLiveData<ImageProfileModel> imageProfileData = new MutableLiveData<>();

    public MutableLiveData<ImageProfileModel> getImageProfileData() {
        return imageProfileData;
    }

    public void uploadImageProfile(ImageProfileModel imageProfileModel) {
        API api = ApiInstance.getRetrofitInstance().create(API.class);
        Call<ImageProfileModel> call = api.uploadImageProfile(imageProfileModel);

        call.enqueue(new Callback<ImageProfileModel>() {
            @Override
            public void onResponse(Call<ImageProfileModel> call, Response<ImageProfileModel> response) {
                if (response.isSuccessful()) {
                    imageProfileData.postValue(response.body());
                } else {
                    imageProfileData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<ImageProfileModel> call, Throwable t) {
                imageProfileData.postValue(null);
            }
        });
    }
}
