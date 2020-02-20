package com.silvertak.relationshipsmanager.customView

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.silvertak.relationshipsmanager.customInterface.SearchTextListener

class SearchEditTextView : AppCompatEditText {

    private var isOneSecondlater = false
    private val mHandler : Handler = Handler()
    private val mRunnable : Runnable = Runnable {
        run { mSearchTextListener!!.onSearchTextChanged(text.toString()) }
    }
    var mSearchTextListener : SearchTextListener? = null

    constructor(context : Context) : super(context)

    constructor(context: Context, attrs : AttributeSet) : super(context, attrs)

    override fun onTextChanged(text: CharSequence?, start: Int, lengthBefore: Int, lengthAfter: Int) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)

        isOneSecondlater = false
        mHandler?.let { mHandler!!.removeCallbacks(mRunnable) }
        mHandler?.let { mHandler!!.postDelayed(mRunnable, 300) }
    }
}