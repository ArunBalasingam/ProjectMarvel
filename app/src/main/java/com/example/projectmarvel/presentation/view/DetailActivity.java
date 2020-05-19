package com.example.projectmarvel.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectmarvel.R;
import com.example.projectmarvel.Singletons;
import com.example.projectmarvel.presentation.controller.MainController;
import com.example.projectmarvel.presentation.model.Results;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    private MainController controller;

    private TextView txtDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txtDetail = findViewById(R.id.detail_txt);
        Intent intent = getIntent();
        String resultsJson = intent.getStringExtra("resultsKey");
        Results results= Singletons.getGson().fromJson(resultsJson,Results.class);
        showDetail(results);
    }

    private void showDetail(Results results){
        txtDetail.setText(results.getName()+"\n\n"+results.getDescription() +"\n\nURL: "+results.getUrls());
    }
}
