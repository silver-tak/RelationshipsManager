package com.silvertak.relationshipsmanager.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.silvertak.relationshipsmanager.R;
import com.silvertak.relationshipsmanager.data.PersonRelationshipInfo;
import com.silvertak.relationshipsmanager.databinding.ActivityDetailTransparentBinding;
import com.silvertak.relationshipsmanager.define.StringDefine;
import com.silvertak.relationshipsmanager.viewmodel.DetailTransparentViewModel;

public class DetailTransparentActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityDetailTransparentBinding mBindidng;
    private DetailTransparentViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_transparent);

        mViewModel = ViewModelProviders.of(this).get(DetailTransparentViewModel.class);
        if(getIntent().getParcelableExtra(StringDefine.KEY_PERSON_RELATIONSHIP_INFO) != null)
            mViewModel.setPersonRelationshipInfo((PersonRelationshipInfo)getIntent().getParcelableExtra(StringDefine.KEY_PERSON_RELATIONSHIP_INFO));

        mBindidng = DataBindingUtil.setContentView(this, R.layout.activity_detail_transparent);
        mBindidng.setDetailTransparentActivity(this);
        mBindidng.setDetailViewModel(mViewModel);
        mBindidng.setPersonRelationshipInfo(mViewModel.getPersonRelationshipInfo());
        mBindidng.setLifecycleOwner(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.transparentLayout :
                finish(); break;
            case R.id.sendSmsBtn :
                startActivity(new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:" + mViewModel.getPersonRelationshipInfo().getContactInfo().getPhoneNumber())));
                break;
            case R.id.sendPrefixSmsBtn : break;
            case R.id.callBtn :
                //startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + info.getPhoneNumber())));
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mViewModel.getPersonRelationshipInfo().getContactInfo().getPhoneNumber())));
                break;
            case R.id.cardViewLayout : break;
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0,R.anim.scale_zoom_out);
    }
}
