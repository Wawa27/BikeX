package com.hassanmorel.bikex.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.hassanmorel.bikex.models.Feature;
import com.hassanmorel.bikex.repositories.FeatureRepository;

import java.util.List;
import java.util.Objects;

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

    public void update(Feature feature){
        repository.update(feature);
    }

    public void deleteAllFeatures() {
        repository.deleteAllFeatures();
    }

    public Feature getFeature(int position) {
        return Objects.requireNonNull(repository.getAllFeatures().getValue()).get(position);
    }

    public LiveData<List<Feature>> getAllFeatures() {
        return allFeatures;
    }

    public int getFeaturesCount() {
        return Objects.requireNonNull(allFeatures.getValue()).size();
    }
}
