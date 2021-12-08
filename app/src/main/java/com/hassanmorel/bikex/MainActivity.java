package com.hassanmorel.bikex;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hassanmorel.bikex.api.ApiClient;
import com.hassanmorel.bikex.api.ApiInterface;
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
    public MutableLiveData<ArrayList<String>> allIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final FeatureAdapter noteAdapter = new NoteAdapter();
        recyclerView.setAdapter(noteAdapter);
    }

    private void addFavoriteFeature() {
        FeatureViewModel featureViewModel = ViewModelProviders
                .of(this)
                .get(FeatureViewModel.class);
        featureViewModel.insert(new Feature("59245426500"));
    }
}