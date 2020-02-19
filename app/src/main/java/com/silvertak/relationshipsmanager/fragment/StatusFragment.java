package com.silvertak.relationshipsmanager.fragment;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;
import com.silvertak.relationshipsmanager.R;
import com.silvertak.relationshipsmanager.adapter.ContactRankingAdapter;
import com.silvertak.relationshipsmanager.customInterface.OnContactInfoClick;
import com.silvertak.relationshipsmanager.data.PersonRelationshipInfo;
import com.silvertak.relationshipsmanager.databinding.FragmentStatusBinding;
import com.silvertak.relationshipsmanager.define.StringDefine;
import com.silvertak.relationshipsmanager.fragment.base.BaseFragment;
import com.silvertak.relationshipsmanager.library.StringLib;
import com.silvertak.relationshipsmanager.view.DetailTransparentActivity;
import com.silvertak.relationshipsmanager.viewmodel.StatusViewModel;

public class StatusFragment extends BaseFragment {

    private static FragmentStatusBinding mBinding;
    private StatusViewModel statusViewModel;

    public StatusFragment() {
    }

    public static StatusFragment newInstance(Bundle args) {
        StatusFragment fragment = new StatusFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        statusViewModel = ViewModelProviders.of(this).get(StatusViewModel.class);
        statusViewModel.setDataArrayList(getArguments());

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_status, container,false);
        mBinding.setVm(statusViewModel);
        mBinding.setLifecycleOwner(this);

        return mBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();

        ContactRankingAdapter contactRankingAdapter = new ContactRankingAdapter();
        contactRankingAdapter.setContactInfoClickListener(new OnContactInfoClick() {
            @Override
            public void onContactInfoClick(PersonRelationshipInfo info) {
                Intent intent = new Intent(getActivity(), DetailTransparentActivity.class);
                intent.putExtra(StringDefine.KEY_PERSON_RELATIONSHIP_INFO, info);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.scale_zoom_in,0);
            }
        });
        mBinding.MostContactRankingRecyclerView.setAdapter(contactRankingAdapter);
        statusViewModel.prepareVisibleData();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @androidx.databinding.BindingAdapter({"setScore"})
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
