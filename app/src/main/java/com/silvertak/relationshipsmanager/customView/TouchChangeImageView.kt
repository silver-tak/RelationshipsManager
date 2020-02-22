package com.silvertak.relationshipsmanager.customView

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.silvertak.relationshipsmanager.R

class TouchChangeImageView : AppCompatImageView, View.OnTouchListener {

    private var nNormalImageId: Int = 0
    private var nClickedImageId: Int = 0
    private var onClickListener: OnClickListener? = null

    constructor(context: Context) : super(context) {
        this.scaleType = ScaleType.FIT_CENTER
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        this.scaleType = ScaleType.FIT_CENTER
        nNormalImageId = context.obtainStyledAttributes(attrs, R.styleable.TouchChangeImageView).getResourceId(R.styleable.TouchChangeImageView_normalSrc, 0)
        nClickedImageId = context.obtainStyledAttributes(attrs, R.styleable.TouchChangeImageView).getResourceId(R.styleable.TouchChangeImageView_clickedSrc, 0)
        Glide.with(context).load(nNormalImageId).into(this)

        this.setOnTouchListener(this)
    }

    override fun setOnClickListener(l: OnClickListener?) {
        onClickListener = l
    }

    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        if (motionEvent.action == MotionEvent.ACTION_DOWN || motionEvent.action == MotionEvent.ACTION_MOVE)
            Glide.with(view.context).load(nClickedImageId).into(view as TouchChangeImageView)
        else {
            Glide.with(view.context).load(nNormalImageId).into(view as TouchChangeImageView)
            if (onClickListener != null) onClickListener!!.onClick(view)
        }
        return true
    }
}
