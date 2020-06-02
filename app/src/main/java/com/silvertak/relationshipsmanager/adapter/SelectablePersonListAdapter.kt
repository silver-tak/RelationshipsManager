package com.silvertak.relationshipsmanager.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.annotation.RequiresApi
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
import com.silvertak.relationshipsmanager.R
import com.silvertak.relationshipsmanager.customInterface.OnContactInfoClick
import com.silvertak.relationshipsmanager.vo.PersonRelationshipInfo
import com.silvertak.relationshipsmanager.databinding.SelectablePersonItemBinding
import com.silvertak.relationshipsmanager.library.StringLib

class SelectablePersonListAdapter(private val mContext: Context) : RecyclerView.Adapter<SelectablePersonListAdapter.CustomViewHolder>(), OnItemTouchListener {
    private var contactInfos = ObservableArrayList<PersonRelationshipInfo>()
    private var selectInfos = ObservableArrayList<PersonRelationshipInfo>()
    private val contactInfoClickListener: OnContactInfoClick? = null
    private var strSearchText = ""
    fun setContactInfos(infos: ObservableArrayList<PersonRelationshipInfo>) {
        contactInfos = infos
    }

    fun setSelectInfos(infos: ObservableArrayList<PersonRelationshipInfo>) {
        selectInfos = infos
    }

    fun executeSearch(strSearchText: String) {
        this.strSearchText = strSearchText
        notifyDataSetChanged()
    }

    private fun isSearchTextContain(info: PersonRelationshipInfo): Boolean {
        if (info.contactInfo == null || StringLib.isEmpty(info.contactInfo.name)
                || StringLib.isEmpty(info.contactInfo.phoneNumber)) return false
        return if (info.contactInfo.name.contains(strSearchText)
                || info.contactInfo.phoneNumber.contains(strSearchText)) true else false
    }

    private val isSearchMode: Boolean
        private get() = if (StringLib.isEmpty(strSearchText)) false else true

    private val searchCount: Int
        private get() {
            var nResult = 0
            for (info in contactInfos) if (isSearchTextContain(info)) nResult++
            return nResult
        }

    private fun getNextData(position: Int): PersonRelationshipInfo? {
        var nSearchCount = 0
        var resultInfo: PersonRelationshipInfo? = null
        for (i in contactInfos.indices) {
            val info = contactInfos[i]
            if (isSearchTextContain(info)) {
                if (position == nSearchCount) {
                    resultInfo = info
                    break
                }
                nSearchCount++
            }
        }
        return resultInfo
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val info = if (isSearchMode) getNextData(position) else contactInfos[position]
        holder.bind(info, mContext)
        holder.mBinding.cardview.setOnClickListener {
            val info = contactInfos[position]
            if (contactInfos[position].selected.value!!) {
                info.setSelected(false)
                holder.setSelectValue(false)
                if (selectInfos.contains(info)) selectInfos.remove(info)
            } else {
                info.setSelected(true)
                holder.setSelectValue(true)
                selectInfos.add(info)
            }
        }
        //if(!isSearchMode())
        setAnimation(holder.itemView)
    }

    private fun setAnimation(view: View) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 300
        view.startAnimation(anim)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val selectablePersonItemBinding = SelectablePersonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(selectablePersonItemBinding)
    }

    override fun getItemCount(): Int {
        return if (isSearchMode) searchCount else contactInfos.size
    }

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        return true
    }

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
    inner class CustomViewHolder(val mBinding: SelectablePersonItemBinding) : RecyclerView.ViewHolder(mBinding.root) {
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(info: PersonRelationshipInfo?, context: Context) {
            mBinding.personRelationshipInfo = info
            initBackgroundColor(info, context)
        }
        @RequiresApi(Build.VERSION_CODES.M)
        private fun initBackgroundColor(info: PersonRelationshipInfo?, context: Context) {
            if (info!!.selected.value!!) mBinding.backgroundLayout.setBackgroundColor(context.getColor(R.color.hashtag))
            else mBinding.backgroundLayout.setBackgroundColor(context.getColor(R.color.fontColor))
        }

        fun setSelectValue(bValue: Boolean) {
            mBinding.personRelationshipInfo.setSelected(bValue)
        }

    }

}