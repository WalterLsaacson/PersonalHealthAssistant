package com.guanyin.sardar.pha.mine.model;


import java.io.Serializable;


public class IndividualInfo implements Serializable {
    private String age;
    private String sex;
    private String petName;
    private String height;
    private String weight;
    private String id;

    // 第二次增加
    private String waistLine;
    private String pulse;
    private String systolicPressure;
    private String diastolicPressure;
    private String bloodSugar;
    private String TC;

    // 第三次增加
    private int waterIntake;
    private int sleepTime;
    private int runDuration;
    private int healthMark;

    @Override
    public String toString() {
        return "IndividualInfo{" +
                "age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                ", petName='" + petName + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", id='" + id + '\'' +
                ", waistLine='" + waistLine + '\'' +
                ", pulse='" + pulse + '\'' +
                ", systolicPressure='" + systolicPressure + '\'' +
                ", diastolicPressure='" + diastolicPressure + '\'' +
                ", bloodSugar='" + bloodSugar + '\'' +
                ", TC='" + TC + '\'' +
                ", waterIntake=" + waterIntake +
                ", sleepTime=" + sleepTime +
                ", runDuration=" + runDuration +
                ", healthMark=" + healthMark +
                '}';
    }

    public String getTC() {
        return TC;
    }

    public void setTC(String TC) {
        this.TC = TC;
    }


    public String getSystolicPressure() {
        return systolicPressure;
    }

    public void setSystolicPressure(String systolicPressure) {
        this.systolicPressure = systolicPressure;
    }

    public String getDiastolicPressure() {
        return diastolicPressure;
    }

    public void setDiastolicPressure(String diastolicPressure) {
        this.diastolicPressure = diastolicPressure;
    }

    public int getWaterIntake() {
        return waterIntake;
    }

    public void setWaterIntake(int waterIntake) {
        this.waterIntake = waterIntake;
    }

    public int getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    public int getRunDuration() {
        return runDuration;
    }

    public void setRunDuration(int runDuration) {
        this.runDuration = runDuration;
    }

    public int getHealthMark() {
        return healthMark;
    }

    public void setHealthMark(int healthMark) {
        this.healthMark = healthMark;
    }


    public String getWaistLine() {
        return waistLine;
    }

    public void setWaistLine(String waistLine) {
        this.waistLine = waistLine;
    }


    public String getPulse() {
        return pulse;
    }

    public void setPulse(String pulse) {
        this.pulse = pulse;
    }


    public String getBloodSugar() {
        return bloodSugar;
    }

    public void setBloodSugar(String bloodSugar) {
        this.bloodSugar = bloodSugar;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
