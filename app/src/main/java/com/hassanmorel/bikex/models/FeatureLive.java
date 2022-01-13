package com.hassanmorel.bikex.models;

public class FeatureLive {
    private final int dayCount;
    private final int hourCount;
    private final int yearCount;

    public FeatureLive(int dayCount, int hourCount, int yearCount) {
        this.dayCount = dayCount;
        this.hourCount = hourCount;
        this.yearCount = yearCount;
    }

    public int getDayCount() {
        return dayCount;
    }

    public int getHourCount() {
        return hourCount;
    }

    public int getYearCount() {
        return yearCount;
    }
}
