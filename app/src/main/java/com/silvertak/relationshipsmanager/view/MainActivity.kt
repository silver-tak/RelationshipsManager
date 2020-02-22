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
    private lateinit var baseFragmentViewPagerAdapter: BaseFragmentViewPagerAdapter
    private val mFragmentArrayList = ArrayList<BaseFragment>()
    private val mFragmentTitleArrayList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModel = ViewModelProviders.of(this@MainActivity).get(MainViewModel::class.java!!)
        mainBinding!!.vm = mainViewModel
        //mainBinding!!.lifecycleOwner = this

        setTabLayoutStyle()
        setFragmentAdapter(intent.getBundleExtra("data"))
    }

    private fun setTabLayoutStyle() {
        mainBinding!!.tabLayout.setupWithViewPager(mainBinding!!.viewPager)
        mainBinding!!.tabLayout.setSelectedTabIndicatorColor(resources.getColor(R.color.background))
        mainBinding!!.tabLayout.setTabTextColors(resources.getColor(R.color.thickgray), resources.getColor(R.color.pointColor))
    }

    private fun setFragmentAdapter(args: Bundle?) {
        mFragmentArrayList.add(StatusFragment.newInstance(args))
        mFragmentTitleArrayList.add("현황")
        mFragmentArrayList.add(GroupModifyFragment.newInstance(args))
        mFragmentTitleArrayList.add("그룹 편집")
        mFragmentArrayList.add(SettingFragment.newInstance(args))
        mFragmentTitleArrayList.add("설정")

        baseFragmentViewPagerAdapter = BaseFragmentViewPagerAdapter(supportFragmentManager)
        baseFragmentViewPagerAdapter.setFragmentArrayList(mFragmentArrayList)
        baseFragmentViewPagerAdapter.setFragmentTitleArrayList(mFragmentTitleArrayList)
        mainBinding.viewPager.adapter = baseFragmentViewPagerAdapter
    }


}
