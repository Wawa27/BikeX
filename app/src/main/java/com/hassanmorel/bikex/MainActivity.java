package com.hassanmorel.bikex;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
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
        fab.setOnClickListener(view -> {
            if (isList) {
                fab.setImageResource(R.drawable.list_icon_foreground);
                recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            } else {
                fab.setImageResource(R.drawable.grid_icon);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
            }
            isList = !isList;
            featureAdapter.setList(isList);
        });

        featureViewModel.getAllFeatures().observe(this, (features) -> {
            featureAdapter.setFeatures(features);
        });
        recyclerView.setAdapter(featureAdapter);
    }
}