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
    private String BMI;
    private String BFR;
    private String waistLine;
    private String temperature;
    private String pulse;
    private String systolicPressure;
    private String diastolicPressure;
    private String bloodSugar;
    private String TC;
    private String TG;
    private String LDL_L;
    private String HDL_C;

    // 第三次增加
    private String waterIntake;
    private String sleepTime;
    private String runDuration;
    private String healthMark;

    public String getTC() {
        return TC;
    }

    public void setTC(String TC) {
        this.TC = TC;
    }

    public String getTG() {
        return TG;
    }

    public void setTG(String TG) {
        this.TG = TG;
    }

    public String getLDL_L() {
        return LDL_L;
    }

    public void setLDL_L(String LDL_L) {
        this.LDL_L = LDL_L;
    }

    public String getHDL_C() {
        return HDL_C;
    }

    public void setHDL_C(String HDL_C) {
        this.HDL_C = HDL_C;
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

    @Override
    public String toString() {
        return "IndividualInfo{" +
                "age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                ", petName='" + petName + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", id='" + id + '\'' +
                ", BMI='" + BMI + '\'' +
                ", BFR='" + BFR + '\'' +
                ", waistLine='" + waistLine + '\'' +
                ", temperature='" + temperature + '\'' +
                ", pulse='" + pulse + '\'' +
                ", systolicPressure='" + systolicPressure + '\'' +
                ", diastolicPressure='" + diastolicPressure + '\'' +
                ", bloodSugar='" + bloodSugar + '\'' +
                ", TC='" + TC + '\'' +
                ", TG='" + TG + '\'' +
                ", LDL_L='" + LDL_L + '\'' +
                ", HDL_C='" + HDL_C + '\'' +
                ", waterIntake='" + waterIntake + '\'' +
                ", sleepTime='" + sleepTime + '\'' +
                ", runDuration='" + runDuration + '\'' +
                ", healthMark='" + healthMark + '\'' +
                '}';
    }

    public String getHealthMark() {
        return healthMark;
    }

    public void setHealthMark(String healthMark) {
        this.healthMark = healthMark;
    }

    public String getWaterIntake() {
        return waterIntake;
    }

    public void setWaterIntake(String waterIntake) {
        this.waterIntake = waterIntake;
    }

    public String getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(String sleepTime) {
        this.sleepTime = sleepTime;
    }

    public String getRunDuration() {
        return runDuration;
    }

    public void setRunDuration(String runDuration) {
        this.runDuration = runDuration;
    }

    public String getBMI() {
        return BMI;
    }

    public void setBMI(String BMI) {
        this.BMI = BMI;
    }

    public String getBFR() {
        return BFR;
    }

    public void setBFR(String BFR) {
        this.BFR = BFR;
    }

    public String getWaistLine() {
        return waistLine;
    }

    public void setWaistLine(String waistLine) {
        this.waistLine = waistLine;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
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
