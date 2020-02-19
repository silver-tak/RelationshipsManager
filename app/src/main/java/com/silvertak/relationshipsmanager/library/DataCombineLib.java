package com.silvertak.relationshipsmanager.library;

import android.util.Log;

import com.silvertak.relationshipsmanager.data.CallLogInfo;
import com.silvertak.relationshipsmanager.data.ContactInfo;
import com.silvertak.relationshipsmanager.data.PersonRelationshipArray;
import com.silvertak.relationshipsmanager.data.PersonRelationshipInfo;

import java.util.ArrayList;
import java.util.stream.Stream;

public class DataCombineLib {

    public static PersonRelationshipArray combineContactWithCallLog(ArrayList<ContactInfo> contactInfos, ArrayList<CallLogInfo> callLogInfos)
    {
        PersonRelationshipArray resultArray = new PersonRelationshipArray();

        for(ContactInfo contactInfo : contactInfos)
        {
            PersonRelationshipInfo info = new PersonRelationshipInfo();
            info.setContactInfo(contactInfo);

            for(CallLogInfo callLogInfo : callLogInfos)
            {
                String callLogPhoneNumber = StringLib.getOnlyNumber(callLogInfo.getPhoneNumber());
                String contactInfoPhoneNumber = StringLib.getOnlyNumber(contactInfo.getPhoneNumber());
                //Log.i("combineContactWithCallLog", "ca : " + callLogPhoneNumber + ", co : " + contactInfoPhoneNumber);
                if(callLogPhoneNumber.equals(contactInfoPhoneNumber))
                {
                    info.addCallLogInfo(callLogInfo);
                }
            }

            resultArray.add(info);
        }

        return resultArray;
    }
}
