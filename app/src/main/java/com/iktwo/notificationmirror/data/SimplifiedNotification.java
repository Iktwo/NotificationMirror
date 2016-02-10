package com.iktwo.notificationmirror.data;

import android.os.Parcel;
import android.os.Parcelable;

public class SimplifiedNotification implements Parcelable {
    public static final Parcelable.Creator<SimplifiedNotification> CREATOR = new Parcelable.Creator<SimplifiedNotification>() {
        public SimplifiedNotification createFromParcel(Parcel source) {
            return new SimplifiedNotification(source);
        }

        public SimplifiedNotification[] newArray(int size) {
            return new SimplifiedNotification[size];
        }
    };

    private String mTitle;
    private String mText;
    private String mPackageName;

    public SimplifiedNotification() {
    }

    public SimplifiedNotification(String title, String text, String packageName) {
        mTitle = title;
        mText = text;
        mPackageName = packageName;
    }

    protected SimplifiedNotification(Parcel in) {
        this.mTitle = in.readString();
        this.mText = in.readString();
        this.mPackageName = in.readString();
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public String getPackageName() {
        return mPackageName;
    }

    public void setPackageName(String packageName) {
        mPackageName = packageName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mTitle);
        dest.writeString(this.mText);
        dest.writeString(this.mPackageName);
    }

    @Override
    public String toString() {
        return String.format("Title: %s Text: %s PackageName: %s", mTitle, mText, mPackageName);
    }
}
