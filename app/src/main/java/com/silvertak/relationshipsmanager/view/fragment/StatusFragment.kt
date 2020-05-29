package com.silvertak.relationshipsmanager.view.fragment

import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.silvertak.relationshipsmanager.R
import com.silvertak.relationshipsmanager.adapter.ContactRankingAdapter
import com.silvertak.relationshipsmanager.databinding.FragmentStatusBinding
import com.silvertak.relationshipsmanager.define.StringDefine
import com.silvertak.relationshipsmanager.library.StringLib
import com.silvertak.relationshipsmanager.view.DetailTransparentActivity
import com.silvertak.relationshipsmanager.view.fragment.base.BaseFragment
import com.silvertak.relationshipsmanager.viewmodel.StatusViewModel

class StatusFragment : BaseFragment() {
    private lateinit var mBinding: FragmentStatusBinding
    private var statusViewModel: StatusViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        statusViewModel = ViewModelProviders.of(this).get(StatusViewModel::class.java).apply {
            setDataArrayList(arguments)
        }
        mBinding = DataBindingUtil.inflate<FragmentStatusBinding>(inflater, R.layout.fragment_status, container, false)
                .apply {
                    vm = statusViewModel
                    lifecycleOwner = this@StatusFragment
                }
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        ContactRankingAdapter().run {
            setContactInfoClickListener { info ->
                Intent(activity, DetailTransparentActivity::class.java).run {
                    putExtra(StringDefine.KEY_PERSON_RELATIONSHIP_INFO, info)
                    startActivity(this)
                    activity!!.overridePendingTransition(R.anim.scale_zoom_in, 0)
                }
            }
            mBinding.MostContactRankingRecyclerView.adapter = this
            statusViewModel?.prepareVisibleData()
        }

        animationValue.observe(this, Observer<Int> {
            setFaceLevel(it)
            setRelationshipComment(it)
        })
    }

    private fun setFaceLevel(nValue: Int) {
        if (nValue <= 25) mBinding!!.faceLevelIv.setImageResource(R.mipmap.level1)
        else if (nValue <= 50) mBinding!!.faceLevelIv.setImageResource(R.mipmap.level2)
        else if (nValue <= 75) mBinding!!.faceLevelIv.setImageResource(R.mipmap.level3)
        else if (nValue <= 100) mBinding!!.faceLevelIv.setImageResource(R.mipmap.level4)
    }

    private fun setRelationshipComment(nValue: Int) {
        if (nValue <= 25) mBinding!!.scoreCommentTv.setText(R.string.score_1_comment)
        else if (nValue <= 50) mBinding!!.scoreCommentTv.setText(R.string.score_2_comment)
        else if (nValue <= 75) mBinding!!.scoreCommentTv.setText(R.string.score_3_comment)
        else if (nValue <= 100) mBinding!!.scoreCommentTv.setText(R.string.score_4_comment)
    }

    companion object {
        private val animationValue: MutableLiveData<Int> = MutableLiveData()
        fun newInstance(args: Bundle?): StatusFragment {
            val fragment = StatusFragment()
            fragment.arguments = args
            return fragment
        }

        @JvmStatic
        @BindingAdapter("setScore")
        fun setScoreAnimation(view: TextView, strValue: String?) {
            if (StringLib.isEmpty(strValue)) return
            val nValue = Integer.valueOf(strValue!!)
            val valueAnimator = ValueAnimator()
            valueAnimator.setObjectValues(0, nValue)
            valueAnimator.addUpdateListener { animator: ValueAnimator ->
                view.text = animator.animatedValue.toString() + "점"
                animationValue.value = animator.animatedValue as Int
            }
            valueAnimator.interpolator = DecelerateInterpolator()
            valueAnimator.duration = 2000
            valueAnimator.start()
        }
    }
}