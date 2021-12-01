package com.hassanmorel.bikex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.hassanmorel.bikex.api.ApiClient;
import com.hassanmorel.bikex.api.ApiInterface;
import com.hassanmorel.bikex.api.models.ApiFeature;
import com.hassanmorel.bikex.api.models.ApiRequest;

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