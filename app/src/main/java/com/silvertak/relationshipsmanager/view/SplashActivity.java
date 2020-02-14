package com.silvertak.relationshipsmanager.view;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.silvertak.relationshipsmanager.R;
import com.silvertak.relationshipsmanager.library.CallLogLib;
import com.silvertak.relationshipsmanager.library.ContactsLib;

import java.util.List;

public class SplashActivity extends AppCompatActivity {

    private Thread td;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                startMappingContactsData();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                finish();
            }
        };

        TedPermission.with(this)
                .setPermissionListener(permissionListener)
                .setDeniedTitle("권한 거부")
                .setDeniedMessage("권한을 허용하지 않으시면 이용하실 수 없습니다")
                .setDeniedCloseButtonText("닫기")
                .setPermissions(Manifest.permission.READ_CONTACTS, Manifest.permission.READ_CALL_LOG, Manifest.permission.WRITE_CALL_LOG, Manifest.permission.CALL_PHONE)
                .check();
    }

    private void startMappingContactsData()
    {
        final ProgressDialog dialog = new ProgressDialog(SplashActivity.this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(false);
        dialog.show();
        td = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dialog.setMessage("주소록 정보를 동기화 중입니다.");
                    ContactsLib contactsLib = new ContactsLib(SplashActivity.this);
                    contactsLib.contacts();
                    dialog.setMessage("통화기록 정보를 동기화 중입니다.");
                    CallLogLib callLogLib = new CallLogLib(SplashActivity.this);
                    callLogLib.getHistory();
                    dialog.dismiss();
                    startNextActivity();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
        td.start();
    }

    private void startNextActivity()
    {
        td.interrupt();
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }
}
