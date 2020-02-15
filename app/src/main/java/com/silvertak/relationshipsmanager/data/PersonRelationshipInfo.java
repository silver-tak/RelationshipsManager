package com.silvertak.relationshipsmanager.data;

import java.util.ArrayList;

public class PersonRelationshipInfo {

    private ContactInfo mContactInfo;
    private ArrayList<CallLogInfo> mCallLogInfos;

    public PersonRelationshipInfo(ContactInfo contactInfo, ArrayList<CallLogInfo> callLogInfos)
    {
        this.mContactInfo = contactInfo;
        this.mCallLogInfos = callLogInfos;
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
}
