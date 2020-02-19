package com.silvertak.relationshipsmanager.thread;

import android.app.Activity;

import com.silvertak.relationshipsmanager.customInterface.ThreadCallBack;
import com.silvertak.relationshipsmanager.data.ContactInfo;
import com.silvertak.relationshipsmanager.library.ContactsLib;
import com.silvertak.relationshipsmanager.view.MappingDataActivity;

import java.util.ArrayList;

public class ContactThread extends BaseCustomThread {

    public ContactThread(ThreadCallBack threadCallBack, Activity activity)
    {
        super(threadCallBack, activity);
    }

    @Override
    public void run() {
        super.run();

        ContactsLib contactsLib = new ContactsLib(activity);
        ArrayList<ContactInfo> contactInfos = contactsLib.contacts();

        this.mThreadCallBack.onContactMappingComplete(contactInfos);
    }
}
