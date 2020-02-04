package com.silvertak.relationshipsmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                contacts();
                getHistory();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {

            }
        };

        TedPermission.with(this)
                .setPermissionListener(permissionListener)
                .setPermissions(Manifest.permission.READ_CONTACTS, Manifest.permission.READ_CALL_LOG, Manifest.permission.WRITE_CALL_LOG)
                .check();

    }

    /**
     * 주소록 정보 가져오기.
     */
    public void contacts(){
        Cursor cursor = managedQuery(
                ContactsContract.Contacts.CONTENT_URI,
                new String[] {
                        ContactsContract.Contacts._ID,
                        ContactsContract.Contacts.DISPLAY_NAME,
                        ContactsContract.Contacts.PHOTO_ID
                },
                null,
                null,
                ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC"
        );
        while (cursor.moveToNext()){
            try {
                String v_id = cursor.getString(0);
                String v_display_name = cursor.getString(1);
                String v_phone = contactsPhone(v_id);
                String v_email = contactsEmail(v_id);

                System.out.println("id = " + v_id);
                System.out.println("display_name = " + v_display_name);
                System.out.println("phone = " + v_phone);
                System.out.println("email = " + v_email);
            }catch(Exception e) {
                System.out.println(e.toString());
            }
        }
        cursor.close();
    }

    /**
     * 주소록 상세정보(전화번호) 가져오기.
     */
    public String contactsPhone(String p_id){
        String reuslt = null;

        if(p_id==null || p_id.trim().equals("")){
            return reuslt;
        }

        Cursor cursor = managedQuery(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[] {
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                },
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + p_id,
                null,
                null
        );
        while (cursor.moveToNext()){
            try {
                reuslt = cursor.getString(0);
            }catch(Exception e) {
                System.out.println(e.toString());
            }
        }
        cursor.close();

        return reuslt;
    }

    /**
     * 주소록 상세정보(이메일주소) 가져오기.
     */
    public String contactsEmail(String p_id){
        String reuslt = null;

        if(p_id==null || p_id.trim().equals("")){
            return reuslt;
        }

        Cursor cursor = managedQuery(
                ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                new String[] {
                        ContactsContract.CommonDataKinds.Email.DATA
                },
                ContactsContract.CommonDataKinds.Email.CONTACT_ID + "=" + p_id,
                null,
                null
        );
        while (cursor.moveToNext()){
            try {
                reuslt = cursor.getString(0);
            }catch(Exception e) {
                System.out.println(e.toString());
            }
        }
        cursor.close();

        return reuslt;
    }


    /*
     * 안드로이드 수신/발신 정보 가져오기 CallLog 이용
     * androidManifest.xml에 추가
     * <uses-permission android:name="android.permission.READ_CONTACTS" />
     */
    public void getHistory() {
        //String[] projection = { CallLog.Calls.CONTENT_TYPE, CallLog.Calls.NUMBER, CallLog.Calls.DURATION, CallLog.Calls.DATE };

        Cursor cur = managedQuery(
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
                sb.append(", date=").append(millis2Time(cur.getLong(cur.getColumnIndex(CallLog.Calls.DATE)))).append("]");

                System.out.println("call history[" + sb.toString());

                cur.moveToNext();
            }
        }

        cur.close();
    }


    /**
     * 밀리초를 시간문자열 반환
     * @param mills   : 밀리초
     * @param pattern  : 패턴 문자열. "yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static String millis2Time(long mills, String pattern){
        String result = null;

        if(pattern==null || pattern.trim().equals("")){
            pattern = "yyyy-MM-dd HH:mm:ss";
        }

        if(mills > 0){
            SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.KOREA);
            result = (String) formatter.format(new Timestamp(mills));
        }

        return result;
    }

    /**
     * 밀리초를 시간문자열 반환
     * @param mills   : 밀리초
     * @return
     */
    public static String millis2Time(long mills){
        return millis2Time(mills, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 날짜 문자열을 밀리초로 반환.
     * @param date   : "2017-01-01 01:01:01"
     * @return
     */
    public static long Date2Mill(String date) {
        return Date2Mill(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 날짜 문자열을 밀리초로 반환.
     * @param date   : "2017-01-01 01:01:01"
     * @param pattern  : "yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static long Date2Mill(String date, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.KOREA);
        Date trans_date = null;

        if(pattern==null || pattern.trim().equals("")){
            pattern = "yyyy-MM-dd HH:mm:ss";
        }

        try {
            trans_date = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return trans_date.getTime();
    }

}
