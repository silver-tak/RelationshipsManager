package com.silvertak.relationshipsmanager.thread;

import android.app.Activity;

import com.silvertak.relationshipsmanager.customInterface.ThreadCallBack;

public class BaseCustomThread extends Thread {

    protected ThreadCallBack mThreadCallBack;
    protected Activity activity;

    public BaseCustomThread(ThreadCallBack threadCallBack, Activity activity)
    {
        this.mThreadCallBack = threadCallBack;
    }
}
