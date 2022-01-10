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



    public Feature(@NonNull String id, String roadEn, String image) {
        this.id = id;
        this.roadEn = roadEn;
        if (image.equals("https://data.mobility.brussels/media/-")) {
            this.image = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/46/Question_mark_%28black%29.svg/1200px-Question_mark_%28black%29.svg.png";
        } else {
            this.image = image;
        }
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
}
