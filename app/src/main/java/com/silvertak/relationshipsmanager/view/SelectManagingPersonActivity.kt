package com.silvertak.relationshipsmanager.view

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders

import com.silvertak.relationshipsmanager.R
import com.silvertak.relationshipsmanager.customInterface.SearchTextListener
import com.silvertak.relationshipsmanager.databinding.ActivitySelectManagingPersonBinding
import com.silvertak.relationshipsmanager.viewmodel.SelectManagingPersonViewModel

class SelectManagingPersonActivity : AppCompatActivity() {

    private lateinit var mViewModel: SelectManagingPersonViewModel
    private lateinit var mBinding: ActivitySelectManagingPersonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_managing_person)

        mViewModel = ViewModelProviders.of(this).get(SelectManagingPersonViewModel::class.java)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_select_managing_person)
        mBinding.selectManagingPersonActivity = this
        mBinding.selectManagingPersonViewModel = mViewModel

        mBinding.searchEditTextView.mSearchTextListener = SearchTextListener{strValue: String? -> Log.i("SelectManagingPerson", "텍스트감지 : $strValue")}
    }
}
