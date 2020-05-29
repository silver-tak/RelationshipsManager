package com.silvertak.relationshipsmanager.view

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import com.silvertak.relationshipsmanager.R
import com.silvertak.relationshipsmanager.adapter.BaseFragmentViewPagerAdapter
import com.silvertak.relationshipsmanager.databinding.ActivityMainBinding
import com.silvertak.relationshipsmanager.define.StringDefine
import com.silvertak.relationshipsmanager.view.fragment.GroupModifyFragment
import com.silvertak.relationshipsmanager.view.fragment.SettingFragment
import com.silvertak.relationshipsmanager.view.fragment.StatusFragment
import com.silvertak.relationshipsmanager.view.fragment.base.BaseFragment
import com.silvertak.relationshipsmanager.viewmodel.MainViewModel

import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private val mFragmentArrayList = ArrayList<BaseFragment>()
    private val mFragmentTitleArrayList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModel = ViewModelProviders.of(this@MainActivity).get(MainViewModel::class.java)
                .apply {
                    mainBinding.vm = this
                    mainBinding.lifecycleOwner = this@MainActivity
                }

        setTabLayoutStyle()
        setFragmentAdapter(intent.getBundleExtra("data"))
    }

    private fun setTabLayoutStyle() =
        mainBinding.tabLayout.run {
            setupWithViewPager(mainBinding.viewPager)
            setSelectedTabIndicatorColor(resources.getColor(R.color.background))
            setTabTextColors(resources.getColor(R.color.thickgray), resources.getColor(R.color.pointColor))
        }


    private fun setFragmentAdapter(args: Bundle?) = args.apply {
        mFragmentTitleArrayList.run {
            add("현황")
            add("그룹편집")
            add("설정")
        }
        mFragmentArrayList.run {
            add(StatusFragment.newInstance(args))
            add(GroupModifyFragment.newInstance(args))
            add(SettingFragment.newInstance(args))
        }
        BaseFragmentViewPagerAdapter(supportFragmentManager).run {
            setFragmentTitleArrayList(mFragmentTitleArrayList)
            setFragmentArrayList(mFragmentArrayList)
            mainBinding.viewPager.adapter = this
        }
    }
}
