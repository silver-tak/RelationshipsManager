package com.silvertak.relationshipsmanager.view

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.silvertak.relationshipsmanager.R
import com.silvertak.relationshipsmanager.adapter.CallLogAdapter
import com.silvertak.relationshipsmanager.data.PersonRelationshipInfo
import com.silvertak.relationshipsmanager.databinding.ActivityDetailTransparentBinding
import com.silvertak.relationshipsmanager.define.StringDefine
import com.silvertak.relationshipsmanager.viewmodel.DetailTransparentViewModel

class DetailTransparentActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mBindidng: ActivityDetailTransparentBinding
    private lateinit var mViewModel: DetailTransparentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_transparent)

        mViewModel = ViewModelProviders.of(this).get(DetailTransparentViewModel::class.java)
        if (intent.getParcelableExtra<Parcelable>(StringDefine.KEY_PERSON_RELATIONSHIP_INFO) != null)
            mViewModel!!.personRelationshipInfo = intent.getParcelableExtra<Parcelable>(StringDefine.KEY_PERSON_RELATIONSHIP_INFO) as PersonRelationshipInfo?

        mBindidng = DataBindingUtil.setContentView(this, R.layout.activity_detail_transparent)
        mBindidng!!.detailTransparentActivity = this
        mBindidng!!.detailViewModel = mViewModel
        mBindidng!!.personRelationshipInfo = mViewModel!!.personRelationshipInfo
        mBindidng!!.callLogRecyclerView.adapter = CallLogAdapter()
        mBindidng!!.lifecycleOwner = this
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.transparentLayout -> finish()
            R.id.sendSmsBtn -> startActivity(Intent(Intent.ACTION_SENDTO, Uri.parse("sms:" + mViewModel!!.personRelationshipInfo.contactInfo.phoneNumber)))
            R.id.sendPrefixSmsBtn -> {
            }
            R.id.callBtn ->
                //startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + info.getPhoneNumber())));
                startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mViewModel!!.personRelationshipInfo.contactInfo.phoneNumber)))
            R.id.cardViewLayout -> {
            }
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, R.anim.scale_zoom_out)
    }
}
