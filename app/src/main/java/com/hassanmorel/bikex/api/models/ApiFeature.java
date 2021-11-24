package com.hassanmorel.bikex.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiFeature {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("properties")
    @Expose
    private ApiDeviceProperties properties;

    @SerializedName("type")
    @Expose
    private String type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
