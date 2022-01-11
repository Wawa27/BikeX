package com.hassanmorel.bikex;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hassanmorel.bikex.adapters.FeatureAdapter;
import com.hassanmorel.bikex.api.ApiClient;
import com.hassanmorel.bikex.api.ApiInterface;
import com.hassanmorel.bikex.api.models.ApiRequest;
import com.hassanmorel.bikex.viewmodels.FeatureViewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    public MutableLiveData<ArrayList<String>> allIds;
    private FeatureAdapter featureAdapter;
    private boolean isList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);
        RecyclerView recyclerView = findViewById(R.id.recyclid);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        isList = true;
        featureAdapter = new FeatureAdapter();
        featureAdapter.setList(isList);

        FeatureViewModel featureViewModel = ViewModelProviders.of(this).get(FeatureViewModel.class);

        FloatingActionButton fab = findViewById(R.id.fab);
        Context c = this;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isList) {
                    fab.setImageResource(R.drawable.list_icon_foreground);
                    recyclerView.setLayoutManager(new GridLayoutManager(c,2));
                }
                else{
                    fab.setImageResource(R.drawable.grid_icon);
                    recyclerView.setLayoutManager(new LinearLayoutManager(c));
                }
                isList = !isList;
                featureAdapter.setList(isList);
            }});

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ApiRequest> call = apiService.getFeatures();

        call.enqueue(new retrofit2.Callback<ApiRequest>() {
            @Override
            public void onResponse(@NonNull Call<ApiRequest> call, @NonNull Response<ApiRequest> response) {

                assert response.body() != null;

                response.body().getFeatures().forEach(f -> featureViewModel.insert(f.toFeature()));
            }

            @Override
            public void onFailure(@NonNull Call<ApiRequest> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });


        featureViewModel.getAllFeatures().observe(this, (features) -> {
            featureAdapter.setFeatures(features);
        });
        recyclerView.setAdapter(featureAdapter);



    }
}