package com.hassanmorel.bikex.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.hassanmorel.bikex.FeatureRepository;
import com.hassanmorel.bikex.models.Feature;

import java.util.List;

public class FeatureViewModel extends AndroidViewModel {
    private final FeatureRepository repository;
    private final LiveData<List<Feature>> allFeatures;

    public FeatureViewModel(@NonNull Application application) {
        super(application);
        repository = new FeatureRepository(application);
        allFeatures = repository.getAllFeatures();
    }

    public void insert(Feature feature) {
        repository.insert(feature);
    }

    public void delete(Feature feature) {
        repository.delete(feature);
    }

    public void deleteAllFeatures() {
        repository.deleteAllFeatures();
    }

    public LiveData<List<Feature>> getAllFeatures() {
        return allFeatures;
    }
}
