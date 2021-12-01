package com.hassanmorel.bikex;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.hassanmorel.bikex.daos.FeatureDAO;
import com.hassanmorel.bikex.models.Feature;

@Database(entities = {Feature.class}, version = 1)
public abstract class FeatureDatabase extends RoomDatabase {

    // pour créer un Singleton de la BDD
    private static FeatureDatabase instance;

    public abstract FeatureDAO featureDAO();

    // création du singleton de la BDD
    // - synchronized garantit qu'une seule instance sera créée même si plusieurs threads essaient de le faire en parallèle
    // - fallbackToDestructiveMigration permet de créer/recréer la base si ce n'est pas la bonne version
    public static synchronized FeatureDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), FeatureDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;

    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private FeatureDAO featureDAO;

        public PopulateDbAsyncTask(FeatureDatabase db) {
            featureDAO = db.featureDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //featureDAO.insert(new Feature("Note 1"));
            //featureDAO.insert(new Feature("Note 2"));
            //featureDAO.insert(new Feature("Note 3"));
            return null;
        }
    }
}
