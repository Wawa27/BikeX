package com.hassanmorel.bikex.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite_features")
public class Feature {
    @PrimaryKey
    @NonNull
    private final String id;

    public Feature(@NonNull String id) {
        this.id = id;
    }

    @NonNull
    public String getId() {
        return id;
    }
}
