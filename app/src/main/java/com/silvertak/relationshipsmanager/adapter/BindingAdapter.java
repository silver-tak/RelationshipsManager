package com.silvertak.relationshipsmanager.adapter;

import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.silvertak.relationshipsmanager.R;
import com.silvertak.relationshipsmanager.data.PersonRelationshipInfo;
import com.silvertak.relationshipsmanager.library.StringLib;

public class BindingAdapter {
    @androidx.databinding.BindingAdapter({"bind:item"})
    public static void bindItem(RecyclerView recyclerView, ObservableArrayList<PersonRelationshipInfo> infos)
    {
        ContactRankingAdapter adapter = (ContactRankingAdapter)recyclerView.getAdapter();
        if(adapter != null)
            adapter.setContactInfos(infos);
    }

    @androidx.databinding.BindingAdapter({"contact:photo"})
    public static void contactPhoto(ImageView iv, Bitmap bitmap)
    {
        if(bitmap != null)
            Glide.with(iv).load(bitmap).apply(new RequestOptions().circleCrop()).into(iv);
        else
            Glide.with(iv).load(BitmapFactory.decodeResource(iv.getResources(), R.mipmap.unknown_user)).into(iv);
    }
}
