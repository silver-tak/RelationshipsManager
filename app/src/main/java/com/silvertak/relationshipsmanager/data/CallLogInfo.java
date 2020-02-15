package com.silvertak.relationshipsmanager.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class CallLogInfo implements Parcelable {

    private String strPhoneNumber = "";
    private int strCallType = -1;
    private Date callDate;
    private int nCallDuration = -1;

    public CallLogInfo(String phoneNumber, int callType, Date callDate, int callDuration)
    {
        this.strPhoneNumber = phoneNumber;
        this.strCallType = callType;
        this.callDate = callDate;
        this.nCallDuration = callDuration;
    }

    protected CallLogInfo(Parcel in) {
        strPhoneNumber = in.readString();
        strCallType = in.readInt();
        nCallDuration = in.readInt();
    }

    public static final Creator<CallLogInfo> CREATOR = new Creator<CallLogInfo>() {
        @Override
        public CallLogInfo createFromParcel(Parcel in) {
            return new CallLogInfo(in);
        }

        @Override
        public CallLogInfo[] newArray(int size) {
            return new CallLogInfo[size];
        }
    };

    public String getPhoneNumber() {
        return strPhoneNumber;
    }

    public int getCallType() {
        return strCallType;
    }

    public Date getCallDate() {
        return callDate;
    }

    public int getCallDuration() {
        return nCallDuration;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(strPhoneNumber);
        parcel.writeInt(strCallType);
        parcel.writeInt(nCallDuration);
    }
}
