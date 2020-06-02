package com.silvertak.relationshipsmanager.library;

import android.app.Activity;
import android.database.Cursor;
import android.provider.CallLog;
import android.util.Log;

import com.silvertak.relationshipsmanager.vo.CallLogVO;

import java.util.ArrayList;
import java.util.Date;

public class CallLogLib {

    private Activity mActivity;

    public CallLogLib(Activity activity)
    {
        this.mActivity = activity;
    }

    /*
     * 안드로이드 수신/발신 정보 가져오기 CallLog 이용
     * androidManifest.xml에 추가
     * <uses-permission android:name="android.permission.READ_CONTACTS" />
     */
    public void getHistory() {
        //String[] projection = { CallLog.Calls.CONTENT_TYPE, CallLog.Calls.NUMBER, CallLog.Calls.DURATION, CallLog.Calls.DATE };

        Cursor cur = mActivity.managedQuery(
                CallLog.Calls.CONTENT_URI,
                null,
                CallLog.Calls.TYPE + "= ?",
                new String[]{ String.valueOf(CallLog.Calls.INCOMING_TYPE) },  //new String[]{ String.valueOf(CallLog.Calls.OUTGOING_TYPE) },
                CallLog.Calls.DEFAULT_SORT_ORDER
        );

        System.out.println("db count=" + String.valueOf(cur.getCount()));
        System.out.println("db count=" + CallLog.Calls.CONTENT_ITEM_TYPE);
        System.out.println("db count=" + CallLog.Calls.CONTENT_TYPE);

        if(cur.moveToFirst() && cur.getCount() > 0) {
            while(cur.isAfterLast() == false) {
                StringBuffer sb = new StringBuffer();

                sb.append("call type=").append(cur.getString(cur.getColumnIndex(CallLog.Calls.TYPE)));
                sb.append(", cashed name=").append(cur.getString(cur.getColumnIndex(CallLog.Calls.CACHED_NAME)));
                sb.append(", content number=").append(cur.getString(cur.getColumnIndex(CallLog.Calls.NUMBER)));
                sb.append(", duration=").append(cur.getString(cur.getColumnIndex(CallLog.Calls.DURATION)));
                sb.append(", new=").append(cur.getString(cur.getColumnIndex(CallLog.Calls.NEW)));
                sb.append(", date=").append(cur.getLong(cur.getColumnIndex(CallLog.Calls.DATE))).append("]");

                System.out.println("call history[" + sb.toString());

                cur.moveToNext();
            }
        }

        cur.close();
    }

    // CallLog를 반환합니다.
    public ArrayList<CallLogVO> getCallLog() {
        ArrayList<CallLogVO> callLogInfos = new ArrayList<>();

        Cursor managedCursor = mActivity.managedQuery(CallLog.Calls.CONTENT_URI, null,
                null, null, null);
        int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);
        while (managedCursor.moveToNext()) {
            /**
             * CallLog.Calls.OUTGOING_TYPE : 발신
             * CallLog.Calls.INCOMING_TYPE : 수신
             * CallLog.Calls.MISSED_TYPE : 부재중
             */
            String phNumber = managedCursor.getString(number);
            String callType = managedCursor.getString(type);
            String callDate = managedCursor.getString(date);
            Date callDayTime = new Date(Long.valueOf(callDate));
            String callDuration = managedCursor.getString(duration);    // sec

            Log.i("callLog", "type : " + callType + ", number : " + phNumber);

            callLogInfos.add(new CallLogVO(phNumber, Integer.valueOf(callType), callDayTime, Integer.valueOf(callDuration)));
        }
        managedCursor.close();

        return callLogInfos;
    }
}
