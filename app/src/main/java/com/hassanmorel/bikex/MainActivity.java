package com.hassanmorel.bikex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;

import com.hassanmorel.bikex.api.ApiClient;
import com.hassanmorel.bikex.api.ApiInterface;
import com.hassanmorel.bikex.api.models.ApiFeature;
import com.hassanmorel.bikex.api.models.ApiRequest;
import com.hassanmorel.bikex.models.Feature;
import com.hassanmorel.bikex.viewmodels.FeatureViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getAllFeatures();
        addFavoriteFeature();
    }

    private void addFavoriteFeature() {
        FeatureViewModel featureViewModel = ViewModelProviders
                .of(this)
                .get(FeatureViewModel.class);
        featureViewModel.insert(new Feature("59245426500"));
        Log.d("BikeX", "Added !");
    }

    private List<ApiFeature> getAllFeatures() {
        List<ApiFeature> features = new ArrayList<>();

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ApiRequest> call = apiService.getFeatures();

        call.enqueue(new Callback<ApiRequest>() {
            @Override
            public void onResponse(@NonNull Call<ApiRequest> call, @NonNull Response<ApiRequest> response) {
                Log.d("BikeX", "Response");
                assert response.body() != null;

                response.body().getFeatures().forEach(feature -> Log.d("BikeX", feature.toString()));
            }

            @Override
            public void onFailure(@NonNull Call<ApiRequest> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });

        return features;
    }
}