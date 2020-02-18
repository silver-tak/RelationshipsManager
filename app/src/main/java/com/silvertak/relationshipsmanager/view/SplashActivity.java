package com.silvertak.relationshipsmanager.view;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.silvertak.relationshipsmanager.R;
import com.silvertak.relationshipsmanager.library.CallLogLib;
import com.silvertak.relationshipsmanager.library.ContactsLib;

import java.util.List;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                startNextActivity();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                finish();
            }
        };

        final TedPermission.Builder tedPermission = TedPermission.with(this)
                .setPermissionListener(permissionListener)
                .setDeniedTitle("권한 거부")
                .setDeniedMessage("권한을 허용하지 않으시면 이용하실 수 없습니다")
                .setDeniedCloseButtonText("닫기")
                .setPermissions(Manifest.permission.READ_CONTACTS, Manifest.permission.READ_CALL_LOG, Manifest.permission.WRITE_CALL_LOG, Manifest.permission.CALL_PHONE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tedPermission.check();
            }
        }, 1500);
    }

    private void startNextActivity()
    {
        startActivity(new Intent(SplashActivity.this, MappingDataActivity.class));
        overridePendingTransition(0, 0);
        finish();
    }
}
