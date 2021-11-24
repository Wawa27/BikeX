package com.hassanmorel.bikex.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiDeviceProperties {
    @SerializedName("active")
    @Expose
    private boolean active;

    @SerializedName("basic_schema")
    @Expose
    private String basicSchema;

    @SerializedName("descr_en")
    @Expose
    private String englishDescription;

    @SerializedName("descr_fr")
    @Expose
    private String frenchDescription;

    @SerializedName("descr_nl")
    @Expose
    private String dutchDescription;

    @SerializedName("detailed_schema")
    @Expose
    private String detailedSchema;

    @SerializedName("device_name")
    @Expose
    private String deviceName;

    @SerializedName("lane_schema")
    @Expose
    private String laneSchema;

    @SerializedName("picture_1")
    @Expose
    private String firstPicture;

    @SerializedName("picture_2")
    @Expose
    private String secondPicture;

    @SerializedName("road_en")
    @Expose
    private String frenchRoadName;

    @SerializedName("road_fr")
    @Expose
    private String englishRoadName;

    @SerializedName("road_nl")
    @Expose
    private String dutchRoadName;

    public ApiDeviceProperties() {

    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getBasicSchema() {
        return basicSchema;
    }

    public void setBasicSchema(String basicSchema) {
        this.basicSchema = basicSchema;
    }

    public String getEnglishDescription() {
        return englishDescription;
    }

    public void setEnglishDescription(String englishDescription) {
        this.englishDescription = englishDescription;
    }

    public String getFrenchDescription() {
        return frenchDescription;
    }

    public void setFrenchDescription(String frenchDescription) {
        this.frenchDescription = frenchDescription;
    }

    public String getDutchDescription() {
        return dutchDescription;
    }

    public void setDutchDescription(String dutchDescription) {
        this.dutchDescription = dutchDescription;
    }

    public String getDetailedSchema() {
        return detailedSchema;
    }

    public void setDetailedSchema(String detailedSchema) {
        this.detailedSchema = detailedSchema;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getLaneSchema() {
        return laneSchema;
    }

    public void setLaneSchema(String laneSchema) {
        this.laneSchema = laneSchema;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        if (firstPicture.equals("https://data.mobility.brussels/media/-")) {
            this.firstPicture = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/46/Question_mark_%28black%29.svg/1200px-Question_mark_%28black%29.svg.png";
        } else {
            this.firstPicture = firstPicture;
        }
    }

    public String getSecondPicture() {
        return secondPicture;
    }

    public void setSecondPicture(String secondPicture) {
        this.secondPicture = secondPicture;
    }

    public String getFrenchRoadName() {
        return frenchRoadName;
    }

    public void setFrenchRoadName(String frenchRoadName) {
        this.frenchRoadName = frenchRoadName;
    }

    public String getEnglishRoadName() {
        return englishRoadName;
    }

    public void setEnglishRoadName(String englishRoadName) {
        this.englishRoadName = englishRoadName;
    }

    public String getDutchRoadName() {
        return dutchRoadName;
    }

    public void setDutchRoadName(String dutchRoadName) {
        this.dutchRoadName = dutchRoadName;
    }
}