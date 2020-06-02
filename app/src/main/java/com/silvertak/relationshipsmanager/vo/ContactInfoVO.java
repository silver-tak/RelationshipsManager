package com.silvertak.relationshipsmanager.vo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;

import com.silvertak.relationshipsmanager.library.StringLib;

public class ContactInfoVO implements Parcelable {

    private String strId = "";
    private String strName = "";
    private String strPhoneNumber = "";
    private String strPhotoId = "";
    private String strEmail = "";

    private byte[] photoBytes = new byte[]{};

    protected ContactInfoVO(Parcel in) {
        strId = in.readString();
        strName = in.readString();
        strPhoneNumber = in.readString();
        strPhotoId = in.readString();
        strEmail = in.readString();
        photoBytes = new byte[in.readInt()];
        in.readByteArray(photoBytes);
    }

    public static final Creator<ContactInfoVO> CREATOR = new Creator<ContactInfoVO>() {
        @Override
        public ContactInfoVO createFromParcel(Parcel in) {
            return new ContactInfoVO(in);
        }

        @Override
        public ContactInfoVO[] newArray(int size) {
            return new ContactInfoVO[size];
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
        parcel.writeInt(photoBytes.length);
        parcel.writeByteArray(photoBytes);
    }

    public ContactInfoVO(String id, String name, String phoneNumber, String photoId, String email)
    {
        if(!StringLib.isEmpty(id)) this.strId = id;
        if(!StringLib.isEmpty(name)) this.strName = name;
        if(!StringLib.isEmpty(phoneNumber)) this.strPhoneNumber = phoneNumber;
        if(!StringLib.isEmpty(photoId)) this.strPhotoId = photoId;
        if(!StringLib.isEmpty(email)) this.strEmail = email;
    }

    public void setPhotoBytes(byte[] bytes)
    {
        this.photoBytes = bytes;
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

    public Bitmap getPhotoBitmap() {
        if(photoBytes != null && photoBytes.length > 0)
            return BitmapFactory.decodeByteArray(photoBytes, 0, photoBytes.length);
        else return null;
    }
}
