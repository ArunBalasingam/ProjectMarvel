package com.example.projectmarvel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showlist();
        makeApiCall();

    }
    private void showlist(){
        recyclerView = (RecyclerView) findViewById(R.id.Recycler_View);
        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        List<String> input = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            input.add("Test" + i);
        }// define an adapter
        mAdapter = new ListAdapter(input);
        recyclerView.setAdapter(mAdapter);
    }


    private void makeApiCall(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

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
                    Toast.makeText(getApplicationContext(), "API SUCCESS", Toast.LENGTH_SHORT).show();
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

    private void showError(){
        Toast.makeText(getApplicationContext(), "API ERROR" ,Toast.LENGTH_SHORT).show();
    }

}
