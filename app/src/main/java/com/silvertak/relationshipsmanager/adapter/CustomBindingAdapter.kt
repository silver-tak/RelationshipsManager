package com.silvertak.relationshipsmanager.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.CallLog
import android.widget.ImageView

import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.silvertak.relationshipsmanager.R
import com.silvertak.relationshipsmanager.vo.PersonRelationshipInfo

class CustomBindingAdapter
{
    object BindingAdapter {

        @JvmStatic
        @androidx.databinding.BindingAdapter("bind:item")
        fun bindItem(recyclerView: RecyclerView, infos: ObservableArrayList<PersonRelationshipInfo>) {
            val adapter = recyclerView.adapter as ContactRankingAdapter?
            adapter?.setContactInfos(infos)
        }

        @JvmStatic
        @androidx.databinding.BindingAdapter("contact:photo")
        fun contactPhoto(iv: ImageView, bitmap: Bitmap?) {
            if (bitmap != null)
                Glide.with(iv).load(bitmap).apply(RequestOptions().circleCrop()).into(iv)
            else
                Glide.with(iv).load(BitmapFactory.decodeResource(iv.resources, R.mipmap.unknown_user)).into(iv)
        }

        @JvmStatic
        @androidx.databinding.BindingAdapter("bind:callType")
        fun bindCallType(iv: ImageView, nCallType: Int) {
            when (nCallType) {
                CallLog.Calls.INCOMING_TYPE -> {
                }
                CallLog.Calls.OUTGOING_TYPE -> {
                }
                CallLog.Calls.MISSED_TYPE -> {
                }
                else -> {
                }
            }
        }
    }
}
