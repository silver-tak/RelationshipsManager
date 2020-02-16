package com.silvertak.relationshipsmanager.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class PersonRelationshipInfo implements Parcelable, Comparable{

    private ContactInfo mContactInfo;
    private ArrayList<CallLogInfo> mCallLogInfos = new ArrayList<>();

    public PersonRelationshipInfo()
    {

    }

    public PersonRelationshipInfo(ContactInfo contactInfo, ArrayList<CallLogInfo> callLogInfos)
    {
        this.mContactInfo = contactInfo;
        this.mCallLogInfos = callLogInfos;
    }

    @Override
    public int compareTo(Object o) {
        if(((PersonRelationshipInfo)o).getCallLogInfos().size() > this.mCallLogInfos.size())
            return 1;
        else if(((PersonRelationshipInfo)o).getCallLogInfos().size() == this.mCallLogInfos.size())
            return 0;
        else
            return -1;
    }

    public ContactInfo getContactInfo() {
        return mContactInfo;
    }

    public void setContactInfo(ContactInfo mContactInfo) {
        this.mContactInfo = mContactInfo;
    }

    public ArrayList<CallLogInfo> getCallLogInfos() {
        return mCallLogInfos;
    }

    public void setCallLogInfos(ArrayList<CallLogInfo> mCallLogInfos) {
        this.mCallLogInfos = mCallLogInfos;
    }

    public void addCallLogInfo(CallLogInfo callLogInfo)
    {
        this.mCallLogInfos.add(callLogInfo);
    }

    protected PersonRelationshipInfo(Parcel in) {
        mContactInfo = in.readParcelable(ContactInfo.class.getClassLoader());
        mCallLogInfos = in.createTypedArrayList(CallLogInfo.CREATOR);
    }

    public static final Creator<PersonRelationshipInfo> CREATOR = new Creator<PersonRelationshipInfo>() {
        @Override
        public PersonRelationshipInfo createFromParcel(Parcel in) {
            return new PersonRelationshipInfo(in);
        }

        @Override
        public PersonRelationshipInfo[] newArray(int size) {
            return new PersonRelationshipInfo[size];
        }
    };

    /**
     * 2회 이상 연락한 전화번호인가?
     * @return
     */
    public boolean hasBeenContactOver2Times()
    {
        if(mCallLogInfos.size() > 1)
            return true;
        else return false;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(mContactInfo, i);
        parcel.writeTypedList(mCallLogInfos);
    }
}
