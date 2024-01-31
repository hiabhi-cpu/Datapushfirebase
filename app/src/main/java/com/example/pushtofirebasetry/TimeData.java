package com.example.pushtofirebasetry;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class TimeData {
    String temp;
    String time;

    public TimeData(String temp, String time) {
        this.temp = temp;
        this.time = time;
    }

    public TimeData() {
    }

    public String getTemp() {
        return temp;
    }

    public String getTime() {
        return time;
    }
}
