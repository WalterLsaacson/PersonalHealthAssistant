package com.guanyin.sardar.pha.alert.model;


import android.os.Parcel;
import android.os.Parcelable;

public class Clock implements Parcelable {

    private String mTitle;
    private String mDate;
    private String mId;
    private String mMusic;
    private boolean mOpened;

    public Clock() {

    }
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

    @Override
    public int describeContents() {
        return 0;
    }

    //private String mTitle;
    //private String mDate;
    //private String mId;
    //private String mMusic;
    //private boolean mOpened;
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeString(mDate);
        dest.writeString(mId);
        dest.writeString(mMusic);
        dest.writeBooleanArray(new boolean[]{mOpened});
    }

    public static final Parcelable.Creator<Clock> CREATOR = new Parcelable.Creator<Clock>() {
        public Clock createFromParcel(Parcel in) {
            return new Clock(in);
        }

        public Clock[] newArray(int size) {
            return new Clock[size];
        }
    };

    private Clock(Parcel in) {
        mTitle = in.readString();
        mDate = in.readString();
        mId = in.readString();
        mMusic = in.readString();
        in.readBooleanArray(new boolean[]{mOpened});
    }
}
