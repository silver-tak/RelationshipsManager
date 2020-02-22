package com.silvertak.relationshipsmanager.viewmodel;

import android.os.Bundle;

import androidx.lifecycle.ViewModel;

import com.silvertak.relationshipsmanager.data.CallLogInfo;
import com.silvertak.relationshipsmanager.data.ContactInfo;
import com.silvertak.relationshipsmanager.data.PersonRelationshipArray;
import com.silvertak.relationshipsmanager.data.PersonRelationshipInfo;
import com.silvertak.relationshipsmanager.define.StringDefine;

import java.util.ArrayList;

public class BaseViewModel extends ViewModel {

    protected ArrayList<ContactInfo> contactInfos = new ArrayList<>();
    protected ArrayList<CallLogInfo> callLogInfos = new ArrayList<>();
    protected PersonRelationshipArray personRelationshipInfos = new PersonRelationshipArray();

    public void setDataArrayList(Bundle bundle)
    {
        if(bundle.getParcelableArrayList("contactInfos") != null)
            contactInfos = bundle.getParcelableArrayList("contactInfos");
        if(bundle.getParcelableArrayList("callLogInfos") != null)
            callLogInfos = bundle.getParcelableArrayList("callLogInfos");
        if(bundle.getParcelableArrayList(StringDefine.KEY_PERSON_RELATIONSHIP_INFO_ARRAY) != null)
        {
            ArrayList<PersonRelationshipInfo> array = bundle.getParcelableArrayList(StringDefine.KEY_PERSON_RELATIONSHIP_INFO_ARRAY);
            personRelationshipInfos = new PersonRelationshipArray(array);
        }
    }

    public PersonRelationshipArray getPersonRelationshipInfos()
    {
        return personRelationshipInfos;
    }

}
