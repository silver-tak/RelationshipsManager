package com.silvertak.relationshipsmanager.view;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.silvertak.relationshipsmanager.R;

import java.util.List;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
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
                .setPermissions(Manifest.permission.READ_CONTACTS, Manifest.permission.READ_CALL_LOG, Manifest.permission.WRITE_CALL_LOG)
                .check();
    }
}
