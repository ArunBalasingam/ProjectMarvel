package com.example.projectmarvel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    static final String BASE_URL = "https://gateway.marvel.com/";
    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SharedPreferences sharedPreferences;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("application_esiea", Context.MODE_PRIVATE);
        gson = new GsonBuilder()
                .setLenient()
                .create();
        List<Results> resultsList = getDataFromCache();
        if(resultsList != null) {
            showlist(resultsList);
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

    private void showlist(List<Results> caracList){
        recyclerView = (RecyclerView) findViewById(R.id.Recycler_View);
        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new ListAdapter(caracList);
        recyclerView.setAdapter(mAdapter);
    }


    private void makeApiCall(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        MarvelAPI marvelAPI = retrofit.create(MarvelAPI.class);

        Call<RestMarvelResponse> call = marvelAPI.getMarvelResponse();
        call.enqueue(new Callback<RestMarvelResponse>() {
            @Override
            public void onResponse(Call<RestMarvelResponse> call, Response<RestMarvelResponse> response) {
                if(response.isSuccessful() && response.body()!=null) {
                    RestMarvelResponse marvelResponse = response.body();
                    savelist(marvelResponse.getData().getResults());
                    showlist(marvelResponse.getData().getResults());
                }else{
                     showError();
                }
            }
            @Override
            public void onFailure(Call<RestMarvelResponse> call, Throwable t) {
                showError();
            }
        });

    }

    private void savelist(List<Results> resultsList) {
        String jsonString = gson.toJson(resultsList);
        sharedPreferences
                .edit()
                .putString(Constants.KEY_RESULTS_LIST, jsonString)
                .apply();
        Toast.makeText(getApplicationContext(), "list saved" ,Toast.LENGTH_SHORT).show();

    }

    private void showError(){
        Toast.makeText(getApplicationContext(), "API ERROR" ,Toast.LENGTH_SHORT).show();
    }

}
