package com.example.mediccenter.view_model;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mediccenter.api.API;
import com.example.mediccenter.api.ApiInstance;
import com.example.mediccenter.models.Catalog;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatalogViewModel extends ViewModel {

    MutableLiveData<List<Catalog>> getCatalogData = new MutableLiveData<>();

    public MutableLiveData<List<Catalog>> getCatalogData() {
        return getCatalogData;
    }

    public void getCatalog() {
        API api = ApiInstance.getRetrofitInstance().create(API.class);
        Call<List<Catalog>> call = api.getCatalog();
        call.enqueue(new Callback<List<Catalog>>() {
            @Override
            public void onResponse(Call<List<Catalog>> call, Response<List<Catalog>> response) {
                if (response.isSuccessful()) {
                    getCatalogData().postValue(response.body());
                }
                else {
                    getCatalogData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<Catalog>> call, Throwable t) {
                getCatalogData.postValue(null);
            }
        });
    }

    public void searchUser(String searchText) {
        API retrofitService = ApiInstance.getRetrofitInstance().create(API.class);
        Call<List<Catalog>> call = retrofitService.searchUser(searchText);
        call.enqueue(new Callback<List<Catalog>>() {
            @Override
            public void onResponse(@NonNull Call<List<Catalog>> call, @NonNull Response<List<Catalog>> response) {
                if (response.isSuccessful()) {
                    getCatalogData.postValue(response.body());
                }
                else {
                    getCatalogData.postValue(null);
                }
            }
            @Override
            public void onFailure(@NonNull Call<List<Catalog>> call, @NonNull Throwable t) {
                getCatalogData.postValue(null);
            }
        });
    }
}
