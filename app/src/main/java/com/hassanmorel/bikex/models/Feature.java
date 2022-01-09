package com.hassanmorel.bikex.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.hassanmorel.bikex.api.models.ApiDeviceProperties;

@Entity(tableName = "features")
public class Feature {
    @PrimaryKey
    @NonNull
    private final String id;

    private final String roadEn;

    private final String image;

    private final boolean isFavorite;


    public Feature(@NonNull String id, String roadEn, String image) {
        this.id = id;
        this.roadEn = roadEn;
        this.image = image;
        this.isFavorite = false;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public String getRoadEn() {
        return roadEn;
    }

    public String getImage() {
        return image;
    }

    public boolean isFavorite() { return isFavorite; }
}
