package com.example.android.quakereport;

import java.util.Date;

public class Earthquake {
    private double mMagnitude;

    private String mLocation;

    private long mDate;

    private String mUrl;

    public Earthquake(double quakeMagnitude, String quakeLocation, long date,String url){
        mMagnitude=quakeMagnitude;
        mLocation=quakeLocation;
        mDate=date;
        mUrl=url;
    }

    public String getQuakeLocation(){return mLocation;
    }
    public double getQuakeMagnitude(){return mMagnitude;}

    public long getDate(){return  mDate;}

    public String getUrl(){
        return mUrl;
    }
}
