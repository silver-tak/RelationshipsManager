package com.silvertak.relationshipsmanager.fragment;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.silvertak.relationshipsmanager.R;
import com.silvertak.relationshipsmanager.adapter.ContactRankingAdapter;
import com.silvertak.relationshipsmanager.customInterface.OnContactInfoClick;
import com.silvertak.relationshipsmanager.data.ContactPersonInfo;
import com.silvertak.relationshipsmanager.databinding.FragmentStatusBinding;
import com.silvertak.relationshipsmanager.fragment.base.BaseFragment;
import com.silvertak.relationshipsmanager.library.StringLib;
import com.silvertak.relationshipsmanager.viewmodel.StatusViewModel;

public class StatusFragment extends BaseFragment {

    private static FragmentStatusBinding mBinding;
    private StatusViewModel statusViewModel;
    private ObservableArrayList<ContactPersonInfo> mostContactRankingList;
    private ObservableArrayList<ContactPersonInfo> needContactRankingList;

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

        mostContactRankingList = new ObservableArrayList<>();
        mBinding.setMostContactRankingList(mostContactRankingList);
        needContactRankingList = new ObservableArrayList<>();
        mBinding.setNeedContactRankingList(needContactRankingList);

        ContactRankingAdapter contactRankingAdapter = new ContactRankingAdapter();
        contactRankingAdapter.setContactInfoClickListener(new OnContactInfoClick() {
            @Override
            public void onContactInfoClick(ContactPersonInfo info) {
                //startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + info.getPhoneNumber())));
                startActivity(new Intent("android.intent.action.DIAL", Uri.parse("tel:" + info.getPhoneNumber())));
            }
        });
        mBinding.MostContactRankingRecyclerView.setAdapter(contactRankingAdapter);

        mBinding.setLifecycleOwner(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                statusViewModel.setRelationshipScore(88);
                mostContactRankingList.add(new ContactPersonInfo("홍길동", "2020. 02. 15", "010-1111-1111"));
                mostContactRankingList.add(new ContactPersonInfo("홍길동", "2020. 02. 15", "010-2222-2222"));
                mostContactRankingList.add(new ContactPersonInfo("홍길동", "2020. 02. 15", "010-3333-3333"));
                mostContactRankingList.add(new ContactPersonInfo("홍길동", "2020. 02. 15", "010-4444-4444"));
                mostContactRankingList.add(new ContactPersonInfo("홍길동", "2020. 02. 15", "010-5555-5555"));
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
                setFaceLevel((int)vm.getAnimatedValue());
                setRelationshipComment((int)vm.getAnimatedValue());
            }
        });
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.setDuration(2000);
        valueAnimator.start();
    }

    @BindingAdapter({"bind:item"})
    public static void bindItem(RecyclerView recyclerView, ObservableArrayList<ContactPersonInfo> infos)
    {
        ContactRankingAdapter adapter = (ContactRankingAdapter)recyclerView.getAdapter();
        if(adapter != null)
            adapter.setContactPersonInfos(infos);
    }

    private static void setFaceLevel(int nValue)
    {
        if(nValue <= 25 )
            mBinding.faceLevelIv.setImageResource(R.mipmap.level1);
        else if(nValue <= 50)
            mBinding.faceLevelIv.setImageResource(R.mipmap.level2);
        else if(nValue <= 75)
            mBinding.faceLevelIv.setImageResource(R.mipmap.level3);
        else if(nValue <= 100)
            mBinding.faceLevelIv.setImageResource(R.mipmap.level4);
    }


    private static void setRelationshipComment(int nValue)
    {
        if(nValue <= 25 )
            mBinding.scoreCommentTv.setText(R.string.score_1_comment);
        else if(nValue <= 50)
            mBinding.scoreCommentTv.setText(R.string.score_2_comment);
        else if(nValue <= 75)
            mBinding.scoreCommentTv.setText(R.string.score_3_comment);
        else if(nValue <= 100)
            mBinding.scoreCommentTv.setText(R.string.score_4_comment);
    }
}
