package com.silvertak.relationshipsmanager.fragment;

import android.animation.ValueAnimator;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.silvertak.relationshipsmanager.R;
import com.silvertak.relationshipsmanager.databinding.FragmentStatusBinding;
import com.silvertak.relationshipsmanager.fragment.base.BaseFragment;
import com.silvertak.relationshipsmanager.library.StringLib;
import com.silvertak.relationshipsmanager.viewmodel.StatusViewModel;

public class StatusFragment extends BaseFragment {

    private FragmentStatusBinding mBinding;
    private StatusViewModel statusViewModel;

    public StatusFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        statusViewModel = ViewModelProviders.of(this).get(StatusViewModel.class);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_status, container,false);
        mBinding.setVm(statusViewModel);
        mBinding.setLifecycleOwner(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                statusViewModel.setRelationshipScore(75);
            }
        },1000);

        return mBinding.getRoot();
    }

    @BindingAdapter({"setScore"})
    public static void setScoreAnimation(final TextView view, final String strValue) {
        if(StringLib.isEmpty(strValue))
            return;

        final int nValue = Integer.valueOf(strValue);
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setObjectValues(0, nValue);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator vm) {
                view.setText(vm.getAnimatedValue() + "점");
            }
        });
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.setDuration(2000);
        valueAnimator.start();
    }
}
