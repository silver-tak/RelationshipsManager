package com.silvertak.relationshipsmanager.thread;

import com.silvertak.relationshipsmanager.customInterface.ThreadCallBack;
import com.silvertak.relationshipsmanager.vo.CallLogVO;
import com.silvertak.relationshipsmanager.vo.ContactInfoVO;
import com.silvertak.relationshipsmanager.vo.PersonRelationshipInfo;
import com.silvertak.relationshipsmanager.library.DataCombineLib;

import java.util.ArrayList;

public class CallLogThread extends Thread {

    private ThreadCallBack mThreadCallBack;
    private ArrayList<ContactInfoVO> contactInfos;
    private ArrayList<CallLogVO> callLogInfos;

    public CallLogThread(ThreadCallBack threadCallBack, ArrayList<ContactInfoVO> contactInfos, ArrayList<CallLogVO> callLogInfos)
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
