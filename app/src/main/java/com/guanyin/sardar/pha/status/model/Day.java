package com.guanyin.sardar.pha.status.model;


public class Day {
    private int date;
    private int water;
    private int sleep;
    private int step;

    public Day(int date) {
        this.date = date;
    }

    public Day() {
    }

    @Override
    public String toString() {
        return "Day{" +
                "date='" + date + '\'' +
                ", water=" + water +
                ", sleep=" + sleep +
                ", step=" + step +
                '}';
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public int getSleep() {
        return sleep;
    }

    public void setSleep(int sleep) {
        this.sleep = sleep;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }
}
