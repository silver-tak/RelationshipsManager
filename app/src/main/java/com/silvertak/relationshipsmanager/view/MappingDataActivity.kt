package com.silvertak.relationshipsmanager.view

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.silvertak.relationshipsmanager.R
import com.silvertak.relationshipsmanager.define.StringDefine
import com.silvertak.relationshipsmanager.library.CallLogLib
import com.silvertak.relationshipsmanager.library.ContactsLib
import com.silvertak.relationshipsmanager.library.DataCombineLib
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class MappingDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapping_data)

        ProgressDialog(this).apply {
            setProgressStyle(ProgressDialog.STYLE_SPINNER)
            setCancelable(false)
            show()
        }.run {
            this.setMessage("인간관계 점수를 측정중입니다......")
            Observable.just(DataCombineLib.combineContactWithCallLog(ContactsLib(this@MappingDataActivity).contacts, CallLogLib(this@MappingDataActivity).callLog))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        this.dismiss()
                        startNextActivity(Bundle().apply { putParcelableArrayList(StringDefine.KEY_PERSON_RELATIONSHIP_INFO_ARRAY, it) })
                    }
        }
    }

    private fun startNextActivity(bundle: Bundle) {
        Intent(this@MappingDataActivity, MainActivity::class.java)
                .apply {
                    putExtra("data", bundle)
                    startActivity(this)
                    finish()
                }
    }
}