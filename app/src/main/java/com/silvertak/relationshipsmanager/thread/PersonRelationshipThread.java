package com.silvertak.relationshipsmanager.thread;

import android.app.Activity;

import com.silvertak.relationshipsmanager.customInterface.ThreadCallBack;
import com.silvertak.relationshipsmanager.data.CallLogInfo;
import com.silvertak.relationshipsmanager.library.CallLogLib;

import java.util.ArrayList;

public class PersonRelationshipThread extends BaseCustomThread {

    public PersonRelationshipThread(ThreadCallBack threadCallBack, Activity activity)
    {
        super(threadCallBack, activity);
    }

    @Override
    public void run() {
        super.run();

        CallLogLib callLogLib = new CallLogLib(activity);
        ArrayList<CallLogInfo> callLogInfos = callLogLib.getCallLog();

        this.mThreadCallBack.onCallLogMappingComplete(callLogInfos);
    }
}
