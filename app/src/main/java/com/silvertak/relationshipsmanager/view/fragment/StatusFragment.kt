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
import com.silvertak.relationshipsmanager.Consts
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
    private lateinit var statusViewModel: StatusViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        statusViewModel = ViewModelProviders.of(this).get(StatusViewModel::class.java).apply {
            setDataArrayList(arguments)
        }
        mBinding = DataBindingUtil.inflate<FragmentStatusBinding>(inflater, R.layout.fragment_status, container, false)
                .apply {
                    vm = statusViewModel
                    lifecycleOwner = this@StatusFragment
                    statusViewModel.relationshipScore.observe(this@StatusFragment, Observer<Int> {
                        setFaceLevel(it)
                        setRelationshipComment(it)

                        ValueAnimator().run {
                            setObjectValues(0, it?.let { Integer.valueOf(it) })
                            addUpdateListener { animator: ValueAnimator ->
                                this@apply.scoreTv.text = animator.animatedValue.toString() + "점"
                            }
                            interpolator = DecelerateInterpolator()
                            duration = Consts.VALUE_ANIMATION_DURATION.toLong()
                            start()
                        }
                    })
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
            statusViewModel.prepareVisibleData()
        }


    }

    private fun setFaceLevel(nValue: Int) {
        when(nValue)
        {
            in 0..25 -> {mBinding.faceLevelIv.setImageResource(R.mipmap.level1)}
            in 26..50 -> {mBinding.faceLevelIv.setImageResource(R.mipmap.level2)}
            in 51..75 -> {mBinding.faceLevelIv.setImageResource(R.mipmap.level3)}
            in 76..100 -> {mBinding.faceLevelIv.setImageResource(R.mipmap.level4)}
            else -> {mBinding.faceLevelIv.setImageResource(R.mipmap.level4)}
        }
    }

    private fun setRelationshipComment(nValue: Int) {
        when(nValue)
        {
            in 0..25 -> {mBinding.scoreCommentTv.text = getString(R.string.score_1_comment)}
            in 26..50 -> {mBinding.scoreCommentTv.text = getString(R.string.score_2_comment)}
            in 51..75 -> {mBinding.scoreCommentTv.text = getString(R.string.score_3_comment)}
            in 76..100 -> {mBinding.scoreCommentTv.text = getString(R.string.score_4_comment)}
            else -> {mBinding.scoreCommentTv.text = getString(R.string.score_4_comment)}
        }
    }

    companion object {
        fun newInstance(args: Bundle?): StatusFragment {
            val fragment = StatusFragment()
            fragment.arguments = args
            return fragment
        }
    }
}