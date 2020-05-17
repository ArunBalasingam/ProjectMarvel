package com.example.projectmarvel.data;

import com.example.projectmarvel.presentation.model.RestMarvelResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MarvelAPI {
    @GET("v1/public/characters?ts=1&apikey=d80e888b2dd909636a4719c10f8f0773&hash=fa83ed4e6fcceb985e395d2a416641a5")
    Call<RestMarvelResponse> getMarvelResponse();
}
