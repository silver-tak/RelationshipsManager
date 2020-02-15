package com.silvertak.relationshipsmanager.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class ContactInfo implements Parcelable {

    private String strId = "";
    private String strName = "";
    private String strPhoneNumber = "";
    private String strPhotoId = "";
    private String strEmail = "";

    protected ContactInfo(Parcel in) {
        strId = in.readString();
        strName = in.readString();
        strPhoneNumber = in.readString();
        strPhotoId = in.readString();
        strEmail = in.readString();
    }

    public static final Creator<ContactInfo> CREATOR = new Creator<ContactInfo>() {
        @Override
        public ContactInfo createFromParcel(Parcel in) {
            return new ContactInfo(in);
        }

        @Override
        public ContactInfo[] newArray(int size) {
            return new ContactInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(strId);
        parcel.writeString(strName);
        parcel.writeString(strPhoneNumber);
        parcel.writeString(strPhotoId);
        parcel.writeString(strEmail);
    }

    public ContactInfo(String id, String name, String phoneNumber, String photoId, String email)
    {
        this.strId = id;
        this.strName = name;
        this.strPhoneNumber = phoneNumber;
        this.strPhotoId = photoId;
        this.strEmail = email;
    }

    public String getName() {
        return strName;
    }

    public String getPhoneNumber() {
        return strPhoneNumber;
    }

    public String getId() {
        return strId;
    }

    public String getPhotoId() {
        return strPhotoId;
    }

    public String getEmail() {
        return strEmail;
    }
}
