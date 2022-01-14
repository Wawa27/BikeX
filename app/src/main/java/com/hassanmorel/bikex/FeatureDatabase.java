package com.hassanmorel.bikex;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.hassanmorel.bikex.api.ApiClient;
import com.hassanmorel.bikex.api.ApiInterface;
import com.hassanmorel.bikex.api.models.ApiRequest;
import com.hassanmorel.bikex.daos.FeatureDAO;
import com.hassanmorel.bikex.models.Feature;

import retrofit2.Call;
import retrofit2.Response;

@Database(entities = {Feature.class}, version = 4)
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
