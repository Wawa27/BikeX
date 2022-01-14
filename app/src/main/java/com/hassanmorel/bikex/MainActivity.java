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
import com.hassanmorel.bikex.models.Feature;
import com.hassanmorel.bikex.viewmodels.FeatureViewModel;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
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

        FeatureViewModel featureViewModel = ViewModelProviders.of(this).get(FeatureViewModel.class);

        isList = true;
        featureAdapter = new FeatureAdapter(featureViewModel);
        featureAdapter.setList(isList);

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

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        apiService
                .getFeatures()
                .toObservable()
                .flatMapIterable(ApiRequest::getFeatures)
                .map(ApiRequest.ApiFeature::toFeature)
                .subscribeOn(Schedulers.io())
                .subscribe(feature -> {
                    System.out.println("feature = " + feature);
                    featureViewModel.insert(feature);
                });

        featureViewModel.getAllFeatures().observe(this, (features) -> {
            featureAdapter.setFeatures(features);
        });

        recyclerView.setAdapter(featureAdapter);
    }
}