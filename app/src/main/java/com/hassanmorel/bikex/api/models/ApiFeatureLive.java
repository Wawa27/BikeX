package com.hassanmorel.bikex.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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

    };

    public featureLiveData getData() {
        return data;
    }
}
