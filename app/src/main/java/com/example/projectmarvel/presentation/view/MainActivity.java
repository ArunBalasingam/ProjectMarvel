package com.example.projectmarvel.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.projectmarvel.R;
import com.example.projectmarvel.Singletons;
import com.example.projectmarvel.presentation.controller.MainController;
import com.example.projectmarvel.presentation.model.Results;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    private MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controller= new MainController(
                this,
                Singletons.getGson(),
                Singletons.getSharedPreferences(getApplicationContext() ));
        controller.onStart();

    }



    public void showlist(List<Results> caracList){
        recyclerView = (RecyclerView) findViewById(R.id.Recycler_View);
        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new ListAdapter(caracList, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Results item) {
                controller.onItemClick(item);
            }
        });
        recyclerView.setAdapter(mAdapter);
    }



    public void showError(){
        Toast.makeText(getApplicationContext(), "API ERROR" ,Toast.LENGTH_SHORT).show();
    }

    public void navigateToDetails(Results results) {
        Intent myIntent = new Intent(MainActivity.this, DetailActivity.class);
        myIntent.putExtra("resultsKey", Singletons.getGson().toJson(results));
        MainActivity.this.startActivity(myIntent);

    }
}
