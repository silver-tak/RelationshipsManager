package com.silvertak.relationshipsmanager.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.silvertak.relationshipsmanager.R;
import com.silvertak.relationshipsmanager.customInterface.ThreadCallBack;
import com.silvertak.relationshipsmanager.data.CallLogInfo;
import com.silvertak.relationshipsmanager.data.ContactInfo;
import com.silvertak.relationshipsmanager.data.PersonRelationshipArray;
import com.silvertak.relationshipsmanager.data.PersonRelationshipInfo;
import com.silvertak.relationshipsmanager.define.StringDefine;
import com.silvertak.relationshipsmanager.library.CallLogLib;
import com.silvertak.relationshipsmanager.library.ContactsLib;
import com.silvertak.relationshipsmanager.library.DataCombineLib;
import com.silvertak.relationshipsmanager.thread.ContactThread;

import java.util.ArrayList;

public class MappingDataActivity extends AppCompatActivity {

    private Thread td;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapping_data);

        startMappingContactsData();
    }

    private void startMappingContactsData()
    {
        final ProgressDialog dialog = new ProgressDialog(MappingDataActivity.this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(false);
        dialog.show();
        td = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dialog.setMessage("주소록 정보를 동기화 중입니다......");
                    ContactsLib contactsLib = new ContactsLib(MappingDataActivity.this);
                    ArrayList<ContactInfo> contactInfos = contactsLib.contacts();

                    dialog.setMessage("통화기록 정보를 동기화 중입니다......");
                    CallLogLib callLogLib = new CallLogLib(MappingDataActivity.this);
                    ArrayList<CallLogInfo> callLogInfos = callLogLib.getCallLog();

                    dialog.setMessage("인간관계 점수를 측정중입니다......");
                    ArrayList<PersonRelationshipInfo> personRelationshipInfos = DataCombineLib.combineContactWithCallLog(contactInfos, callLogInfos);

                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("contactInfos", contactInfos);
                    bundle.putParcelableArrayList("callLogInfos", callLogInfos);
                    bundle.putParcelableArrayList(StringDefine.KEY_PERSON_RELATIONSHIP_INFO_ARRAY, personRelationshipInfos);

                    dialog.dismiss();
                    startNextActivity(bundle);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
        td.start();
    }

    private void startNextActivity(Bundle bundle)
    {
        td.interrupt();
        Intent intent = new Intent(MappingDataActivity.this, MainActivity.class);
        intent.putExtra("data", bundle);
        startActivity(intent);
        finish();
    }
}
