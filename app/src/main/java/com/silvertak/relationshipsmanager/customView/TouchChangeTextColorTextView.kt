package com.silvertak.relationshipsmanager.customView

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatTextView

import com.silvertak.relationshipsmanager.R

class TouchChangeTextColorTextView : AppCompatTextView, View.OnTouchListener {

    private var nNormalTextColorId: Int = 0
    private var nClickedTextColorId: Int = 0
    private var onClickListener: OnClickListener? = null

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        nNormalTextColorId = context.obtainStyledAttributes(attrs, R.styleable.TouchChangeTextColorTextView).getResourceId(R.styleable.TouchChangeTextColorTextView_normalTextColor, 0)
        nClickedTextColorId = context.obtainStyledAttributes(attrs, R.styleable.TouchChangeTextColorTextView).getResourceId(R.styleable.TouchChangeTextColorTextView_clickedTextColor, 0)

        this.setOnTouchListener(this)
        setNormal(context)
    }

    override fun setOnClickListener(l: OnClickListener?) {
        onClickListener = l
    }

    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        if (motionEvent.action == MotionEvent.ACTION_DOWN || motionEvent.action == MotionEvent.ACTION_MOVE)
            setClicked(view.context)
        else {
            setNormal(view.context)
            if (onClickListener != null) onClickListener!!.onClick(view)
        }
        return true
    }

    private fun setNormal(context: Context) {
        if (nNormalTextColorId != 0)
            setTextColor(context.resources.getColor(nNormalTextColorId))
    }

    private fun setClicked(context: Context) {
        if (nClickedTextColorId != 0)
            setTextColor(context.resources.getColor(nClickedTextColorId))
    }
}
