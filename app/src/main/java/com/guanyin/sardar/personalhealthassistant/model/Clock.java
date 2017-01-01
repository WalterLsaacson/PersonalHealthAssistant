package com.guanyin.sardar.personalhealthassistant.model;


import java.util.Date;

public class Clock {

    private String mTitle;
    private String mDate;
    private String mId;
    private String mMusic;
    private boolean mOpened;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getMusic() {
        return mMusic;
    }

    public void setMusic(String music) {
        mMusic = music;
    }

    public boolean isOpen() {
        return mOpened;
    }

    public void setOpen(boolean open) {
        mOpened = open;
    }
}
