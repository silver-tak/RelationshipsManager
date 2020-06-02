package com.silvertak.relationshipsmanager.vo;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CallLogVO implements Parcelable {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 aa HH:mm");

    private String strPhoneNumber = "";
    private int nCallType = -1;
    private int nCallDuration = -1;
    private Date callDate;
    private String strCallDate = "";

    public CallLogVO(String phoneNumber, int callType, Date callDate, int callDuration)
    {
        this.strPhoneNumber = phoneNumber;
        this.nCallType = callType;
        this.callDate = callDate;
        this.nCallDuration = callDuration;
        this.strCallDate = simpleDateFormat.format(callDate);
    }

    protected CallLogVO(Parcel in) {
        strPhoneNumber = in.readString();
        nCallType = in.readInt();
        nCallDuration = in.readInt();
        try
        {
            strCallDate = in.readString();
            callDate = simpleDateFormat.parse(strCallDate);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
    }

    public static final Creator<CallLogVO> CREATOR = new Creator<CallLogVO>() {
        @Override
        public CallLogVO createFromParcel(Parcel in) {
            return new CallLogVO(in);
        }

        @Override
        public CallLogVO[] newArray(int size) {
            return new CallLogVO[size];
        }
    };

    public String getPhoneNumber() {
        return strPhoneNumber;
    }

    public int getCallType() {
        return nCallType;
    }

    public Date getCallDate() {
        return callDate;
    }

    public int getCallDuration() {
        return nCallDuration;
    }

    public String getCallDateString() {
        return strCallDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(strPhoneNumber);
        parcel.writeInt(nCallType);
        parcel.writeInt(nCallDuration);
        parcel.writeString(strCallDate);
    }
}
