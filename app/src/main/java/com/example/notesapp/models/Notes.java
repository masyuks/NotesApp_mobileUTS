package com.example.notesapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Notes implements Parcelable {
    private String title;
    private String content;

    public Notes(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Notes() {
    }

    protected Notes(Parcel in) {
        title = in.readString();
        content = in.readString();
    }

    public static final Creator<Notes> CREATOR = new Creator<Notes>() {
        @Override
        public Notes createFromParcel(Parcel in) {
            return new Notes(in);
        }

        @Override
        public Notes[] newArray(int size) {
            return new Notes[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(content);
    }
}
