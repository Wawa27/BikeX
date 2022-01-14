package com.hassanmorel.bikex.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hassanmorel.bikex.models.Feature;
import com.hassanmorel.bikex.models.FeatureLive;

import java.util.List;

public class ApiFeatureLive {

    @SerializedName("data")
    @Expose
    private featureLiveData data;

    public class featureLiveData{
        @SerializedName("hour_cnt")
        @Expose
        private int hourCount;

        @SerializedName("day_cnt")
        @Expose
        private int dayCount;

        @SerializedName("year_cnt")
        @Expose
        private int yearCount;

        public featureLiveData() {
        }

        public int getHourCount() {
            return hourCount;
        }

        public int getDayCount() {
            return dayCount;
        }

        public int getYearCount() {
            return yearCount;
        }

        public FeatureLive toFeatureLive(){
            return new FeatureLive(getDayCount(), getHourCount(), getYearCount());
        }

    };

    public featureLiveData getData() {
        return data;
    }


}
