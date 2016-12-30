package com.guanyin.sardar.personalhealthassistant.model;


import java.util.Date;

public class Clock {

    private String mTitle;
    private Date mDate;

    private String mMusic;
    private boolean mOpened;


    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
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
