package com.silvertak.relationshipsmanager.customView

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.widget.LinearLayout

import androidx.appcompat.widget.AppCompatImageView

import com.silvertak.relationshipsmanager.R

class CallTypeCustomView : LinearLayout {

    private lateinit var iv: AppCompatImageView
    private var mContext: Context

    constructor(context: Context) : super(context) {
        this.mContext = context
        addImageView(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        this.mContext = context
        addImageView(context)
    }

    private fun addImageView(context: Context) {
        iv = AppCompatImageView(context)

        val lp = LayoutParams(
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30f, context.resources.displayMetrics).toInt(),
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30f, context.resources.displayMetrics).toInt()
        )
        lp.weight = 1.0f
        lp.gravity = Gravity.CENTER
        iv.layoutParams = lp

        this.addView(iv)
    }

    fun setIncommingType() {
        iv.setImageResource(R.mipmap.call_in)
        this.setBackgroundColor(mContext.resources.getColor(R.color.call_button))
    }

    fun setOutgoingType() {
        iv.setImageResource(R.mipmap.call_out)
        this.setBackgroundColor(mContext.resources.getColor(R.color.hashtag))
    }

    fun setMissedType() {
        iv.setImageResource(R.mipmap.call_miss)
        this.setBackgroundColor(mContext.resources.getColor(R.color.red))
    }
}
