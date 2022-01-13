package com.hassanmorel.bikex.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.hassanmorel.bikex.FeatureDatabase;
import com.hassanmorel.bikex.api.ApiClient;
import com.hassanmorel.bikex.api.ApiInterface;
import com.hassanmorel.bikex.api.models.ApiFeatureLive;
import com.hassanmorel.bikex.api.models.ApiRequest;
import com.hassanmorel.bikex.daos.FeatureDAO;
import com.hassanmorel.bikex.models.Feature;
import com.hassanmorel.bikex.models.FeatureLive;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FeatureRepository {
    private FeatureDAO featureDAO;
    private ApiInterface apiService;

    public FeatureRepository(Application application) {
        FeatureDatabase database = FeatureDatabase.getInstance(application);
        featureDAO = database.featureDAO();

        apiService = ApiClient.getClient().create(ApiInterface.class);
    }

    public void insert(Feature feature) {
        new InsertFeatureAsyncTask(featureDAO).execute(feature);
    }

    public void update(Feature feature) {
        new UpdateFeatureAsyncTask(featureDAO).execute(feature);
    }

    public void delete(Feature feature) {
        new DeleteFeatureAsyncTask(featureDAO).execute(feature);
    }

    public void deleteAllFeatures() {
        new DeleteAllFeaturesAsyncTask(featureDAO).execute();
    }

    public Flowable<List<Feature>> getFeatures() {
        return apiService.getFeatures()
                .map(ApiRequest::getFeatures)
                .flatMapIterable(x -> x)
                .map(ApiRequest.ApiFeature::toFeature)
                .toList()
                .toFlowable()
                .subscribeOn(Schedulers.io());
    }

    public Flowable<FeatureLive> getDetails(String id){
        return apiService.getData(id)
                .map(ApiFeatureLive::getData)
                .map(ApiFeatureLive.featureLiveData::toFeatureLive)
                .subscribeOn(Schedulers.io());
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

    private static class UpdateFeatureAsyncTask extends AsyncTask<Feature, Void, Void> {
        private FeatureDAO featureDAO;

        private UpdateFeatureAsyncTask(FeatureDAO featureDAO) {
            this.featureDAO = featureDAO;
        }

        @Override
        protected Void doInBackground(Feature... notes) {
            featureDAO.update(notes[0]);
            return null;
        }
    }

}
