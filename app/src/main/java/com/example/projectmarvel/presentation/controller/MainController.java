package com.example.projectmarvel.presentation.controller;

import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.projectmarvel.Constants;
import com.example.projectmarvel.Singletons;
import com.example.projectmarvel.presentation.model.RestMarvelResponse;
import com.example.projectmarvel.presentation.model.Results;
import com.example.projectmarvel.presentation.view.MainActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainController {

    private SharedPreferences sharedPreferences;
    private Gson gson;
    private MainActivity view;

    public MainController(MainActivity mainActivity,Gson  gson,SharedPreferences sharedPreferences) {
        this.view= mainActivity;
        this.gson = gson;
        this.sharedPreferences =sharedPreferences;
    }

    public void onStart(){

        List<Results> resultsList = getDataFromCache();
        if(resultsList != null) {
            view.showlist(resultsList);
        }else{
            makeApiCall();
        }

    }
    private List<Results> getDataFromCache() {
        String jsonResults =sharedPreferences.getString(Constants.KEY_RESULTS_LIST, null);

        if(jsonResults==null){
            return null;
        } else {
            Type listType = new TypeToken<List<Results>>() {}.getType();
            List<Results> osef =gson.fromJson(jsonResults, listType);
            return osef;
        }
    }

    private void makeApiCall(){

        Call<RestMarvelResponse> call = Singletons.getMarvelAPI().getMarvelResponse();
        call.enqueue(new Callback<RestMarvelResponse>() {
            @Override
            public void onResponse(Call<RestMarvelResponse> call, Response<RestMarvelResponse> response) {
                if(response.isSuccessful() && response.body()!=null) {
                    RestMarvelResponse marvelResponse = response.body();
                    savelist(marvelResponse.getData().getResults());
                    view.showlist(marvelResponse.getData().getResults());
                }else{
                    view.showError();
                }
            }
            @Override
            public void onFailure(Call<RestMarvelResponse> call, Throwable t) {
                view.showError();
            }
        });

    }

    private void savelist(List<Results> resultsList) {
        String jsonString = gson.toJson(resultsList);
        sharedPreferences
                .edit()
                .putString(Constants.KEY_RESULTS_LIST, jsonString)
                .apply();
        Toast.makeText(view.getApplicationContext(), "list saved" ,Toast.LENGTH_SHORT).show();

    }

    public void onItemClick(Results results){}


}
