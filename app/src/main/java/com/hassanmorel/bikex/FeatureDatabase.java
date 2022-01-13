package com.hassanmorel.bikex;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.hassanmorel.bikex.daos.FeatureDAO;
import com.hassanmorel.bikex.models.Feature;

@Database(entities = {Feature.class}, version = 1)
public abstract class FeatureDatabase extends RoomDatabase {
    private static FeatureDatabase instance;

    public abstract FeatureDAO featureDAO();

    public static synchronized FeatureDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), FeatureDatabase.class, "features")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
