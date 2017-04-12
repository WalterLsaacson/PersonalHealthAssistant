package com.guanyin.sardar.pha.customview;


public class SmartPhoneOS {

    String name;
    int count;
    float angle;


    public SmartPhoneOS(String name, int count) {
        this.name = name;
        this.count = count;
    }


    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
