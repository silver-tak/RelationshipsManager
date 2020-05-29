package com.silvertak.relationshipsmanager.view

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.silvertak.relationshipsmanager.R
import com.silvertak.relationshipsmanager.view.dialog.MessageDialogFragment
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val tedPermission = TedPermission.with(this)
                .setPermissionListener(object : PermissionListener {
                    override fun onPermissionGranted() {
                        //startNextActivity()
                        startCollectDataDialog()
                    }

                    override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                        finish()
                    }
                })
                .setDeniedTitle(getString(R.string.permission_denied))
                .setDeniedMessage(getString(R.string.permission_denied_msg))
                .setDeniedCloseButtonText(getString(R.string.cancel))
                .setPermissions(Manifest.permission.READ_CONTACTS, Manifest.permission.READ_CALL_LOG, Manifest.permission.WRITE_CALL_LOG, Manifest.permission.CALL_PHONE)

        addDisposable(Observable.timer(1500L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe { tedPermission.check() })
    }

    private fun startNextActivity() {
        startActivity(Intent(this@SplashActivity, MappingDataActivity::class.java))
        overridePendingTransition(0, 0)
        finish()
    }

    private fun startCollectDataDialog() {
        MessageDialogFragment.newInstance().show(supportFragmentManager, MessageDialogFragment.TAG)
    }
}