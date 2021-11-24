package com.hassanmorel.bikex.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiRequest {
    @SerializedName("features")
    @Expose
    private List<ApiFeature> apiFeatures;

    public ApiRequest() {

    }

    public List<ApiFeature> getFeatures() {
        return apiFeatures;
    }

    public void setFeatures(List<ApiFeature> apiFeatures) {
        this.apiFeatures = apiFeatures;
    }
}
