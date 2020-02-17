package com.silvertak.relationshipsmanager.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.silvertak.relationshipsmanager.R;
import com.silvertak.relationshipsmanager.databinding.ActivityDetailTransparentBinding;
import com.silvertak.relationshipsmanager.viewmodel.DetailTransparentViewModel;

public class DetailTransparentActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityDetailTransparentBinding mBindidng;
    private DetailTransparentViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_transparent);

        mViewModel = ViewModelProviders.of(this).get(DetailTransparentViewModel.class);

        mBindidng = DataBindingUtil.setContentView(this, R.layout.activity_detail_transparent);
        mBindidng.setDetailTransparentActivity(this);
        mBindidng.setDetailViewModel(mViewModel);
        mBindidng.setLifecycleOwner(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.transparentLayout : finish(); break;
            case R.id.cardViewLayout : break;
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0,0);
    }
}
