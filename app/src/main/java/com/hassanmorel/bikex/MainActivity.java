package com.hassanmorel.bikex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;

import com.hassanmorel.bikex.api.ApiClient;
import com.hassanmorel.bikex.api.ApiInterface;
import com.hassanmorel.bikex.api.models.ApiFeature;
import com.hassanmorel.bikex.api.models.ApiFeatureLive;
import com.hassanmorel.bikex.api.models.ApiRequest;
import com.hassanmorel.bikex.models.Feature;
import com.hassanmorel.bikex.viewmodels.FeatureViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public ArrayList<String> allIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        allIds = new ArrayList<>();
        getAllFeatures();
        allIds.add("CB2105");
        getAllData();
        //addFavoriteFeature();
    }

    private void addFavoriteFeature() {
        FeatureViewModel featureViewModel = ViewModelProviders
                .of(this)
                .get(FeatureViewModel.class);
        //featureViewModel.insert(new Feature("59245426500"));

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

                response.body().getFeatures().forEach(feature -> allIds.add(feature.toString()));
            }

            @Override
            public void onFailure(@NonNull Call<ApiRequest> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });


        return features;
    }
    public void getAllData(){
        for (String id:allIds
             ) {
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<ApiFeatureLive> call = apiService.getData(id);
            call.enqueue(new Callback<ApiFeatureLive>() {
                @Override
                public void onResponse(@NonNull Call<ApiFeatureLive> call, @NonNull Response<ApiFeatureLive> response) {
                    Log.d("Data", "Response");
                    assert response.body() != null;

                    Log.d("COUNT", ""+response.body().getData().getDayCount());
                }

                @Override
                public void onFailure(@NonNull Call<ApiFeatureLive> call, @NonNull Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }
}