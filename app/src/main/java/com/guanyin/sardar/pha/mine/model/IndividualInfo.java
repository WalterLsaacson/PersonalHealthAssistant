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
    private String bloodPressure;
    private String bloodSugar;
    private String bloodFat;


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

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getBloodSugar() {
        return bloodSugar;
    }

    public void setBloodSugar(String bloodSugar) {
        this.bloodSugar = bloodSugar;
    }

    public String getBloodFat() {
        return bloodFat;
    }

    public void setBloodFat(String bloodFat) {
        this.bloodFat = bloodFat;
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

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("petname" + petName);
        stringBuffer.append("sex" + sex);
        stringBuffer.append("age" + age);
        stringBuffer.append("height" + height);
        stringBuffer.append("weight" + weight);
        return stringBuffer.toString();
    }
}
