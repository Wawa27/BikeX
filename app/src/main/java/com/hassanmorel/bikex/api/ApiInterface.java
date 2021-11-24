package com.hassanmorel.bikex.api;

import com.hassanmorel.bikex.api.models.ApiRequest;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("counts/?request=devices")
    Call<ApiRequest> getFeatures();
}
