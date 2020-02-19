package com.silvertak.relationshipsmanager.customInterface;

import com.silvertak.relationshipsmanager.data.CallLogInfo;
import com.silvertak.relationshipsmanager.data.ContactInfo;
import com.silvertak.relationshipsmanager.data.PersonRelationshipInfo;

import java.util.ArrayList;

public interface ThreadCallBack {
    void onContactMappingComplete(ArrayList<ContactInfo> contactInfos);
    void onCallLogMappingComplete(ArrayList<CallLogInfo> callLogInfos);
    void onPersonRelationshipMappingComplete(ArrayList<PersonRelationshipInfo> personRelationshipInfos);
}
