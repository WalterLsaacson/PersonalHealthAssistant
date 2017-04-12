package com.guanyin.sardar.pha.status.model;



public class Day {
    private String date;
    private String water;
    private String sleep;
    private String sport;

    @Override
    public String toString() {
        return "Day{" +
                "date='" + date + '\'' +
                ", water='" + water + '\'' +
                ", sleep='" + sleep + '\'' +
                ", sport='" + sport + '\'' +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public String getSleep() {
        return sleep;
    }

    public void setSleep(String sleep) {
        this.sleep = sleep;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }
}
