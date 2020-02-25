package com.silvertak.relationshipsmanager.adapter;

import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.CallLog;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.silvertak.relationshipsmanager.R;
import com.silvertak.relationshipsmanager.customView.CallTypeCustomView;
import com.silvertak.relationshipsmanager.data.PersonRelationshipInfo;
import com.silvertak.relationshipsmanager.library.StringLib;

public class BindingAdapter {
    @androidx.databinding.BindingAdapter({"bind:contactRankingItem"})
    public static void bindContactRankingItem(RecyclerView recyclerView, ObservableArrayList<PersonRelationshipInfo> infos)
    {
        ContactRankingAdapter adapter = (ContactRankingAdapter)recyclerView.getAdapter();
        if(adapter != null)
            adapter.setContactInfos(infos);
    }

    @androidx.databinding.BindingAdapter({"bind:callLogItem"})
    public static void bindCallLogItem(RecyclerView recyclerView, PersonRelationshipInfo info)
    {
        CallLogAdapter adapter = (CallLogAdapter)recyclerView.getAdapter();
        if(adapter != null)
            adapter.setPersonRelationshipInfo(info);
    }

    @androidx.databinding.BindingAdapter({"contact:photo"})
    public static void contactPhoto(ImageView iv, Bitmap bitmap)
    {
        if(bitmap != null)
            Glide.with(iv).load(bitmap).apply(new RequestOptions().circleCrop()).into(iv);
        else
            Glide.with(iv).load(BitmapFactory.decodeResource(iv.getResources(), R.mipmap.unknown_user)).into(iv);
    }

    @androidx.databinding.BindingAdapter({"bind:callType"})
    public static void callType(CallTypeCustomView cv, int nCallType)
    {
        switch (nCallType)
        {
            case CallLog.Calls.INCOMING_TYPE :
                cv.setIncommingType();
                break;
            case CallLog.Calls.OUTGOING_TYPE :
                cv.setOutgoingType();
                break;
            case CallLog.Calls.MISSED_TYPE :
                cv.setMissedType();
                break;
            default: break;
        }
    }

    @androidx.databinding.BindingAdapter({"bind:callTypeBackground"})
    public static void callTypeBackground(ImageView iv, int nCallType)
    {
        switch (nCallType)
        {
            case CallLog.Calls.INCOMING_TYPE : break;
            case CallLog.Calls.OUTGOING_TYPE : break;
            case CallLog.Calls.MISSED_TYPE : break;
            default: break;
        }
    }

    @androidx.databinding.BindingAdapter({"bind:observablePersonItemList"})
    public static void bindObservablePersonList(RecyclerView recyclerView, ObservableArrayList<PersonRelationshipInfo> infos)
    {
        SelectablePersonListAdapter adapter = (SelectablePersonListAdapter)recyclerView.getAdapter();
        if(adapter != null)
            adapter.setContactInfos(infos);
    }

    @androidx.databinding.BindingAdapter({"bind:observableSelectPersonItemList"})
    public static void bindObservableSelectPersonList(RecyclerView recyclerView, ObservableArrayList<PersonRelationshipInfo> infos)
    {
        SelectablePersonListAdapter adapter = (SelectablePersonListAdapter)recyclerView.getAdapter();
        if(adapter != null)
            adapter.setSelectInfos(infos);
    }

    @androidx.databinding.BindingAdapter({"bind:backgroundType"})
    public static void changePersonItemBackground(RelativeLayout rl, boolean bIsSelect)
    {
        if(bIsSelect) rl.setBackgroundColor(rl.getContext().getResources().getColor(R.color.hashtag));
        else rl.setBackgroundColor(rl.getContext().getResources().getColor(R.color.fontColor));
    }

    @androidx.databinding.BindingAdapter({"bind:groupInfo_memberCount"})
    public static void setGroupInfoMemberCount(TextView tv, int nCount)
    {
        tv.setText(nCount + "명");
    }


}