package com.example.zahramanafi.personaldilemmaresolver;

import android.widget.Button;

import java.sql.Time;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

/**
 * Created by Shorai-4 on 2017-10-18.
 */

public class Dilemma {

    private UUID mId;
    private String mTitle;
    private Date mDate;
    private String PositiveBtn;
    private String NegativeBtn;

    private android.text.format.Time mTime;
    private boolean mSolved;



    public Dilemma() {
        // Generate unique identifier
        mId = UUID.randomUUID();
        mDate = new Date();
        mTime = new android.text.format.Time();
        //  NegativeBtn = "Negative emotion";
        // PositiveBtn = "Positve emotions";

    }

    public UUID getId() {
        return mId;
    }

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

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public String getPositiveBtn() {
        return PositiveBtn;
    }

    public void setPositiveBtn(String positiveBtn) {
        PositiveBtn = positiveBtn;
    }

    public String getNegativeBtn() {
        return NegativeBtn;
    }

    public void setNegativeBtn(String negativeBtn) {
        NegativeBtn = negativeBtn;
    }

    public android.text.format.Time getTime() {
        return mTime;
    }

    public void setTime(android.text.format.Time time) {
        mTime = time;
    }
}