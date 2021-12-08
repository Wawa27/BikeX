package com.hassanmorel.bikex.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hassanmorel.bikex.models.Feature;

import java.util.List;

public class ApiRequest {
    @SerializedName("features")
    @Expose
    private List<ApiFeature> apiFeatures;

    public class ApiFeature {
        @SerializedName("id")
        @Expose
        private String id;

        @SerializedName("properties")
        @Expose
        private ApiDeviceProperties properties;

        public Feature toFeature(){
            return new Feature(id, properties.getEnglishRoadName(), properties.getFirstPicture());
        }

        public ApiFeature() {

        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public ApiDeviceProperties getProperties() {
            return properties;
        }

        public void setProperties(ApiDeviceProperties properties) {
            this.properties = properties;
        }

    }

    public ApiRequest() {
    }

    public List<ApiFeature> getFeatures() {
        return apiFeatures;
    }

    public void setFeatures(List<ApiFeature> apiFeatures) {
        this.apiFeatures = apiFeatures;
    }
}
