package com.hassanmorel.bikex;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.hassanmorel.bikex.daos.FeatureDAO;
import com.hassanmorel.bikex.models.Feature;

import java.util.List;

public class FeatureRepository {
    private FeatureDAO featureDAO;
    private LiveData<List<Feature>> allFeatures;

    public FeatureRepository(Application application) {
        FeatureDatabase database = FeatureDatabase.getInstance(application);
        featureDAO = database.featureDAO();
        allFeatures = featureDAO.getAllFeatures();
    }


    public void insert(Feature feature) {
        new InsertFeatureAsyncTask(featureDAO).execute(feature);
    }

    public void delete(Feature feature) {
        new DeleteFeatureAsyncTask(featureDAO).execute(feature);
    }

    public void deleteAllFeatures() {
        new DeleteAllFeaturesAsyncTask(featureDAO).execute();
    }

    public LiveData<List<Feature>> getAllFeatures() {
        return allFeatures;
    }


    private static class InsertFeatureAsyncTask extends AsyncTask<Feature, Void, Void> {
        private final FeatureDAO featureDAO;

        public InsertFeatureAsyncTask(FeatureDAO featureDAO) {
            this.featureDAO = featureDAO;
        }

        @Override
        protected Void doInBackground(Feature... features) {
            featureDAO.insert(features[0]);
            return null;
        }
    }

    private static class DeleteFeatureAsyncTask extends AsyncTask<Feature, Void, Void> {
        private final FeatureDAO featureDao;

        public DeleteFeatureAsyncTask(FeatureDAO featureDao) {
            this.featureDao = featureDao;
        }

        @Override
        protected Void doInBackground(Feature... features) {
            featureDao.delete(features[0]);
            return null;
        }
    }

    private static class DeleteAllFeaturesAsyncTask extends AsyncTask<Void, Void, Void> {
        private final FeatureDAO featureDao;

        public DeleteAllFeaturesAsyncTask(FeatureDAO featureDao) {
            this.featureDao = featureDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            featureDao.deleteAllFeatures();
            return null;
        }
    }

}
