package com.hassanmorel.bikex.api;

import com.hassanmorel.bikex.api.models.ApiFeatureLive;
import com.hassanmorel.bikex.api.models.ApiRequest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("counts/?request=devices")
    Call<ApiRequest> getFeatures();

    @GET("counts/?request=live")
    Call<ApiFeatureLive> getData(@Query("featureID") String id);
}
