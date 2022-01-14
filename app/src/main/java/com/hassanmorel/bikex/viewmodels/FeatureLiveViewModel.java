package com.hassanmorel.bikex.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;

import com.hassanmorel.bikex.models.Feature;
import com.hassanmorel.bikex.models.FeatureLive;
import com.hassanmorel.bikex.repositories.FeatureRepository;

import java.util.List;

public class FeatureLiveViewModel extends AndroidViewModel {

    private final FeatureRepository repository;

    public FeatureLiveViewModel(@NonNull Application application) {
        super(application);
        repository = new FeatureRepository(application);
    }

    public LiveData<FeatureLive> getFeatureLive(String id) {
        return LiveDataReactiveStreams.fromPublisher(repository.getDetails(id));
    }
}
