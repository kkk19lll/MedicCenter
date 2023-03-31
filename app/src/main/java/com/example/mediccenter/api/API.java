package com.example.mediccenter.api;

import com.example.mediccenter.models.Catalog;
import com.example.mediccenter.models.CreateCardPaitentModel;
import com.example.mediccenter.models.ImageProfileModel;
import com.example.mediccenter.models.News;
import com.example.mediccenter.models.UpdateCardPatientModel;
import com.example.mediccenter.models.UserAuth;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface API {
    @POST("api/signin")
    @Headers({"Accept:application/json", "Content-Type:application/json",
            "Authorization: Bearer 058902a9-a9a7-45f1-a92b-40b8cf7a02d0"})
    Call<UserAuth> createPost(@Body UserAuth userAuth);

    @POST("api/createProfile")
    @Headers({"Accept:application/json", "Content-Type:application/json",
            "Authorization: Bearer 058902a9-a9a7-45f1-a92b-40b8cf7a02d0"})
    Call<CreateCardPaitentModel> createCardPatient(@Body CreateCardPaitentModel createCardPaitentModel);

    @POST("api/updateProfile")
    @Headers({"Accept:application/json", "Content-Type:application/json",
            "Authorization: Bearer 058902a9-a9a7-45f1-a92b-40b8cf7a02d0"})
    Call<UpdateCardPatientModel> updateCardPatient(@Body UpdateCardPatientModel updateCardPatientModel);

    @POST("api/avatar")
    @Headers({"Accept:application/json", "Content-Type:application/json",
            "Authorization: Bearer 058902a9-a9a7-45f1-a92b-40b8cf7a02d0"})
    Call<ImageProfileModel> uploadImageProfile(@Body ImageProfileModel imageProfileModel);

    @GET("api/catalog")
    @Headers({"Accept:application/json", "Content-Type:application/json",
            "Authorization: Bearer 058902a9-a9a7-45f1-a92b-40b8cf7a02d0"})
    Call<List<Catalog>> getCatalog();

    @GET("api/news")
    @Headers({"Accept:application/json", "Content-Type:application/json",
            "Authorization: Bearer 058902a9-a9a7-45f1-a92b-40b8cf7a02d0"})
    Call<List<News>> getNews();

    @GET("api/catalog")
    @Headers({"Accept:application/json", "Content-Type:application/json",
            "Authorization: Bearer 058902a9-a9a7-45f1-a92b-40b8cf7a02d0"})
    Call<List<Catalog>> searchUser(@Query("name") String searchText);
}
