package com.hassanmorel.bikex.daos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.hassanmorel.bikex.api.models.ApiRequest;
import com.hassanmorel.bikex.models.Feature;

import java.util.List;

@Dao
public interface FeatureDAO {

    @Insert
    void insert(Feature feature);

    @Delete
    void delete(Feature feature);

    @Update
    void update(Feature feature);

    @Query("DELETE FROM features")
    void deleteAllFeatures();

    @Query("SELECT * FROM features")
    LiveData<List<Feature>> getAllFeatures();
}