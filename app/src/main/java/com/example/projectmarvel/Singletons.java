package com.example.projectmarvel;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.projectmarvel.data.MarvelAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Singletons {
    private static Gson gsonInstance;
    private static MarvelAPI marvelAPIInstance;
    private static SharedPreferences sharedPreferencesInstance;


    public static Gson getGson() {
        if (gsonInstance == null) {
            gsonInstance = new GsonBuilder()
                    .setLenient()
                    .create();
        }
        return gsonInstance;
    }


    public static MarvelAPI getMarvelAPI(){
        if(marvelAPIInstance==null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();

             marvelAPIInstance = retrofit.create(MarvelAPI.class);
        }
        return marvelAPIInstance;
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        if (sharedPreferencesInstance == null) {
            sharedPreferencesInstance = context.getSharedPreferences("application_esiea", Context.MODE_PRIVATE);
        }
        return sharedPreferencesInstance;
    }

}


