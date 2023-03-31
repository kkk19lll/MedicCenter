package com.example.mediccenter.view_model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mediccenter.api.API;
import com.example.mediccenter.api.ApiInstance;
import com.example.mediccenter.models.CreateCardPaitentModel;
import com.example.mediccenter.models.UpdateCardPatientModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateCardPatientViewModel extends ViewModel {
    private final MutableLiveData<UpdateCardPatientModel> updateCardPatientData = new MutableLiveData<>();

    public MutableLiveData<UpdateCardPatientModel> getUpdateCardPatientData() {
        return updateCardPatientData;
    }

    public void updateUser(UpdateCardPatientModel updateCardPatientModel) {
        API api = ApiInstance.getRetrofitInstance().create(API.class);
        Call<UpdateCardPatientModel> call = api.updateCardPatient(updateCardPatientModel);

        call.enqueue(new Callback<UpdateCardPatientModel>() {
            @Override
            public void onResponse(Call<UpdateCardPatientModel> call, Response<UpdateCardPatientModel> response) {
                if (response.isSuccessful()) {
                    updateCardPatientData.postValue(response.body());
                }
                else {
                    updateCardPatientData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<UpdateCardPatientModel> call, Throwable t) {
                updateCardPatientData.postValue(null);
            }
        });
    }
}
