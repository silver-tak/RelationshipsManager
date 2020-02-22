package com.silvertak.relationshipsmanager.customView

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatTextView

import com.silvertak.relationshipsmanager.R

class TouchChangeBackgroundColorTextView : AppCompatTextView, View.OnTouchListener {

    private var nNormalColorId: Int = 0
    private var nClickedColorId: Int = 0
    private var nNormalBackgroundResourceId: Int = 0
    private var nClickedBackgroundResourceId: Int = 0
    private var onClickListener: OnClickListener? = null

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
            nNormalColorId = context.obtainStyledAttributes(attrs, R.styleable.TouchChangeBackgroundColorTextView).getResourceId(R.styleable.TouchChangeBackgroundColorTextView_normalBackgroundColor, 0)
            nClickedColorId = context.obtainStyledAttributes(attrs, R.styleable.TouchChangeBackgroundColorTextView).getResourceId(R.styleable.TouchChangeBackgroundColorTextView_clickedBackgroundColor, 0)

            nNormalBackgroundResourceId = context.obtainStyledAttributes(attrs, R.styleable.TouchChangeBackgroundColorTextView).getResourceId(R.styleable.TouchChangeBackgroundColorTextView_normalBackgroundResource, 0)
            nClickedBackgroundResourceId = context.obtainStyledAttributes(attrs, R.styleable.TouchChangeBackgroundColorTextView).getResourceId(R.styleable.TouchChangeBackgroundColorTextView_clickedBackgroundResource, 0)

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
        if (nNormalColorId != 0)
            setBackgroundColor(context.resources.getColor(nNormalColorId))
        else
            setBackgroundResource(nNormalBackgroundResourceId)
    }

    private fun setClicked(context: Context) {
        if (nClickedColorId != 0)
            setBackgroundColor(context.resources.getColor(nClickedColorId))
        else
            setBackgroundResource(nClickedBackgroundResourceId)
    }
}
