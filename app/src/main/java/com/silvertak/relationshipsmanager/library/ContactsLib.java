package com.silvertak.relationshipsmanager.library;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;

import com.silvertak.relationshipsmanager.data.ContactInfo;

import java.util.ArrayList;

public class ContactsLib {

    private Activity mActivity;

    public ContactsLib(Activity activity)
    {
        this.mActivity = activity;
    }

    /**
     * 주소록 정보 가져오기.
     */
    public ArrayList<ContactInfo> contacts(){
        ArrayList<ContactInfo> arrayList = new ArrayList<>();

        Cursor cursor = mActivity.managedQuery(
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
                String v_photo_id = cursor.getString(2);
                String v_phone = contactsPhone(v_id);
                String v_email = contactsEmail(v_id);

                ContactInfo contactInfo = new ContactInfo(v_id, v_display_name, v_phone, v_photo_id, v_email);
                if(!StringLib.isEmpty(v_photo_id))
                    contactInfo.setPhotoBytes(queryContactImage(Integer.valueOf(v_photo_id)));

                arrayList.add(contactInfo);

                /*System.out.println("id = " + v_id);
                System.out.println("display_name = " + v_display_name);
                System.out.println("photo_id = " + v_photo_id);
                System.out.println("phone = " + v_phone);
                System.out.println("email = " + v_email);*/
            }catch(Exception e) {
                System.out.println(e.toString());
            }
        }
        cursor.close();

        return arrayList;
    }

    /**
     * PhotoId에 기반하여 photo Bitmap 추출
     * @param imageDataRow
     * @return
     */
    private byte[] queryContactImage(int imageDataRow) {
        Cursor c = mActivity.getContentResolver().query(ContactsContract.Data.CONTENT_URI, new String[] {
                ContactsContract.CommonDataKinds.Photo.PHOTO
        }, ContactsContract.Data._ID + "=?", new String[] {
                Integer.toString(imageDataRow)
        }, null);
        byte[] imageBytes = null;
        if (c != null) {
            if (c.moveToFirst()) {
                imageBytes = c.getBlob(0);
            }
            c.close();
        }

        if (imageBytes != null) {
            //return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            return imageBytes;
        } else {
            return null;
        }
    }

    /**
     * 주소록 상세정보(전화번호) 가져오기.
     */
    public String contactsPhone(String p_id){
        String reuslt = null;

        if(p_id==null || p_id.trim().equals("")){
            return reuslt;
        }

        Cursor cursor = mActivity.managedQuery(
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

        Cursor cursor = mActivity.managedQuery(
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
}
