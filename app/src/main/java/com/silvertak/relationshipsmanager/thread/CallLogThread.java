package com.silvertak.relationshipsmanager.thread;

import android.app.Activity;

import com.silvertak.relationshipsmanager.customInterface.ThreadCallBack;
import com.silvertak.relationshipsmanager.data.CallLogInfo;
import com.silvertak.relationshipsmanager.data.ContactInfo;
import com.silvertak.relationshipsmanager.data.PersonRelationshipInfo;
import com.silvertak.relationshipsmanager.library.CallLogLib;
import com.silvertak.relationshipsmanager.library.ContactsLib;
import com.silvertak.relationshipsmanager.library.DataCombineLib;
import com.silvertak.relationshipsmanager.view.MappingDataActivity;

import java.util.ArrayList;

public class CallLogThread extends Thread {

    private ThreadCallBack mThreadCallBack;
    private ArrayList<ContactInfo> contactInfos;
    private ArrayList<CallLogInfo> callLogInfos;

    public CallLogThread(ThreadCallBack threadCallBack, ArrayList<ContactInfo> contactInfos, ArrayList<CallLogInfo> callLogInfos)
    {
        mThreadCallBack = threadCallBack;
        this.contactInfos = contactInfos;
        this.callLogInfos = callLogInfos;
    }

    @Override
    public void run() {
        super.run();

        ArrayList<PersonRelationshipInfo> personRelationshipInfos = DataCombineLib.combineContactWithCallLog(contactInfos, callLogInfos);
        this.mThreadCallBack.onPersonRelationshipMappingComplete(personRelationshipInfos);
    }
}
