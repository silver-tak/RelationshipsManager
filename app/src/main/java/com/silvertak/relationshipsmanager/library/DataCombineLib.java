package com.silvertak.relationshipsmanager.library;

import com.silvertak.relationshipsmanager.vo.CallLogVO;
import com.silvertak.relationshipsmanager.vo.ContactInfoVO;
import com.silvertak.relationshipsmanager.vo.PersonRelationshipArray;
import com.silvertak.relationshipsmanager.vo.PersonRelationshipInfo;

import java.util.ArrayList;

public class DataCombineLib {

    public static PersonRelationshipArray combineContactWithCallLog(ArrayList<ContactInfoVO> contactInfos, ArrayList<CallLogVO> callLogInfos)
    {
        PersonRelationshipArray resultArray = new PersonRelationshipArray();

        for(ContactInfoVO contactInfo : contactInfos)
        {
            PersonRelationshipInfo info = new PersonRelationshipInfo();
            info.setContactInfo(contactInfo);

            for(CallLogVO callLogInfo : callLogInfos)
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
