package com.hassanmorel.bikex.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;

import com.hassanmorel.bikex.models.Feature;
import com.hassanmorel.bikex.repositories.FeatureRepository;

import java.util.List;
import java.util.Objects;

import io.reactivex.rxjava3.core.BackpressureStrategy;

public class FeatureViewModel extends AndroidViewModel {
    private final FeatureRepository repository;

    public FeatureViewModel(@NonNull Application application) {
        super(application);
        repository = new FeatureRepository(application);
    }

    public void insert(Feature feature) {
        repository.insert(feature);
    }

    public void delete(Feature feature) {
        repository.delete(feature);
    }

    public void update(Feature feature) {
        repository.update(feature);
    }

    public void deleteAllFeatures() {
        repository.deleteAllFeatures();
    }

    public LiveData<List<Feature>> getAllFeatures() {
        return repository.getTkt();
        //return LiveDataReactiveStreams.fromPublisher(repository.getFeatures());
    }
}
