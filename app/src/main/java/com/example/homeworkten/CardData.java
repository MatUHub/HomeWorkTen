package com.example.homeworkten;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class CardData implements Parcelable {

    private String note;
    private String description;

    private Date date;


    public Date getDate() {
        return date;
    }

    public CardData(String note, String description, Date date) {
        this.note = note;
        this.description = description;
        this.date = date;
    }

    protected CardData(Parcel in) {
        note = in.readString();
        description = in.readString();
    }

    public static final Creator<CardData> CREATOR = new Creator<CardData>() {
        @Override
        public CardData createFromParcel(Parcel in) {
            return new CardData(in);
        }

        @Override
        public CardData[] newArray(int size) {
            return new CardData[size];
        }
    };

    public String getNote() {
        return note;
    }

    public String getDescription() {
        return description;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(note);
        parcel.writeString(description);
    }
}
