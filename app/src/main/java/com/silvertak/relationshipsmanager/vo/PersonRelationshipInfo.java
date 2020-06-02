package com.silvertak.relationshipsmanager.vo;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

public class PersonRelationshipInfo implements Parcelable, Comparable{

    private ContactInfoVO mContactInfo;
    private ArrayList<CallLogVO> mCallLogInfos = new ArrayList<>();
    private MutableLiveData<Boolean> isSelected = new MutableLiveData<>();  // RecyclerView List의 항목일 때 선택되었는지 여부

    public PersonRelationshipInfo(ContactInfoVO contactInfo, ArrayList<CallLogVO> callLogInfos)
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

    public ContactInfoVO getContactInfo() {
        return mContactInfo;
    }

    public void setContactInfo(ContactInfoVO mContactInfo) {
        this.mContactInfo = mContactInfo;
    }

    public ArrayList<CallLogVO> getCallLogInfos() {
        return mCallLogInfos;
    }

    public void setCallLogInfos(ArrayList<CallLogVO> mCallLogInfos) {
        this.mCallLogInfos = mCallLogInfos;
    }

    public void addCallLogInfo(CallLogVO callLogInfo)
    {
        this.mCallLogInfos.add(callLogInfo);
    }

    public void setSelected(boolean bValue)
    {
        this.isSelected.setValue(bValue);
    }

    public MutableLiveData<Boolean> getSelected()
    {
        if(isSelected.getValue() == null)
            isSelected.setValue(false);

        return this.isSelected;
    }

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

    protected PersonRelationshipInfo(Parcel in) {
        mContactInfo = in.readParcelable(ContactInfoVO.class.getClassLoader());
        mCallLogInfos = in.createTypedArrayList(CallLogVO.CREATOR);
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
