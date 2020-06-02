package com.silvertak.relationshipsmanager.view

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.view.View

import com.silvertak.relationshipsmanager.R
import com.silvertak.relationshipsmanager.adapter.CallLogAdapter
import com.silvertak.relationshipsmanager.vo.PersonRelationshipInfo
import com.silvertak.relationshipsmanager.databinding.ActivityDetailTransparentBinding
import com.silvertak.relationshipsmanager.define.StringDefine
import com.silvertak.relationshipsmanager.viewmodel.DetailTransparentViewModel

class DetailTransparentActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mBinding: ActivityDetailTransparentBinding
    private lateinit var mViewModel: DetailTransparentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_transparent)

        ViewModelProviders.of(this).get(DetailTransparentViewModel::class.java).apply {
            mViewModel = this
            if (intent.getParcelableExtra<Parcelable>(StringDefine.KEY_PERSON_RELATIONSHIP_INFO) != null)
                this.personRelationshipInfo = intent.getParcelableExtra<Parcelable>(StringDefine.KEY_PERSON_RELATIONSHIP_INFO) as PersonRelationshipInfo?
        }

        DataBindingUtil.setContentView<ActivityDetailTransparentBinding>(this, R.layout.activity_detail_transparent).apply{
            detailTransparentActivity = this@DetailTransparentActivity
            detailViewModel = mViewModel
            personRelationshipInfo = mViewModel.personRelationshipInfo
            callLogRecyclerView.adapter = CallLogAdapter()
            mBinding = this
        }
    }

    override fun onClick(view: View) = view?.let {
        when (it.id) {
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
