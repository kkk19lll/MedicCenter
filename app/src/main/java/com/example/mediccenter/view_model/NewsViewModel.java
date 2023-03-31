package com.example.mediccenter.view_model;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mediccenter.api.API;
import com.example.mediccenter.api.ApiInstance;
import com.example.mediccenter.models.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsViewModel extends ViewModel {
    MutableLiveData<List<News>> getNewsData = new MutableLiveData<>();

    public MutableLiveData<List<News>> getNewsData() {
        return getNewsData;
    }

    public void getNews() {
        API api = ApiInstance.getRetrofitInstance().create(API.class);
        Call<List<News>> call = api.getNews();
        call.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(@NonNull Call<List<News>> call, @NonNull Response<List<News>> response) {
                if (response.isSuccessful()) {
                    getNewsData().postValue(response.body());
                }
                else {
                    getNewsData.postValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<News>> call, @NonNull Throwable t) {
                getNewsData.postValue(null);
            }
        });
    }
}
