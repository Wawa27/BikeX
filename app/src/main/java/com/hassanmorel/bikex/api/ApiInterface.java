package com.hassanmorel.bikex.api;

import com.hassanmorel.bikex.api.models.ApiFeatureLive;
import com.hassanmorel.bikex.api.models.ApiRequest;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("counts/?request=devices")
    Observable<ApiRequest> getFeatures();

    @GET("counts/?request=live")
    Observable<ApiFeatureLive> getData(@Query("featureID") String id);
}
