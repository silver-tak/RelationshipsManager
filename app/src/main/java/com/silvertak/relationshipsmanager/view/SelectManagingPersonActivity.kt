package com.silvertak.relationshipsmanager.view

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders

import com.silvertak.relationshipsmanager.R
import com.silvertak.relationshipsmanager.adapter.SelectablePersonListAdapter
import com.silvertak.relationshipsmanager.customInterface.SearchTextListener
import com.silvertak.relationshipsmanager.databinding.ActivitySelectManagingPersonBinding
import com.silvertak.relationshipsmanager.define.StringDefine
import com.silvertak.relationshipsmanager.library.SharedPreferencesLib
import com.silvertak.relationshipsmanager.library.StringLib
import com.silvertak.relationshipsmanager.viewmodel.SelectManagingPersonViewModel

class SelectManagingPersonActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mViewModel: SelectManagingPersonViewModel
    private lateinit var mBinding: ActivitySelectManagingPersonBinding
    private lateinit var recyclerViewAdapter: SelectablePersonListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_managing_person)

        mViewModel = ViewModelProviders.of(this).get(SelectManagingPersonViewModel::class.java)
        mViewModel.setDataArrayList(intent.getBundleExtra(StringDefine.KEY_DEFAULT_BUNDLE_KEY))
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_select_managing_person)
        mBinding.selectManagingPersonActivity = this
        mBinding.selectManagingPersonViewModel = mViewModel

        mBinding.contactTermSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, resources.getStringArray(R.array.contactTermArray))
        mBinding.contactTermSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            }
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
        mBinding.contactTermSpinner.setSelection(3, true)

        recyclerViewAdapter = SelectablePersonListAdapter(this)
        mBinding.selectablePersonList.adapter = recyclerViewAdapter
        mBinding.searchEditTextView.mSearchTextListener = SearchTextListener{strValue: String? -> recyclerViewAdapter.executeSearch(strValue)}

        mBinding.selectManagingPersonViewModel = mViewModel
        mBinding.lifecycleOwner = this
    }

    override fun onResume() {
        super.onResume()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, R.anim.scale_zoom_out)
    }

    override fun onClick(v: View?) {
        when(v!!.id)
        {
            R.id.backgroundLayout, R.id.cancelBtn -> {
                finish()
            }
            R.id.confirmBtn -> {
                executeSaveGroup()
                finish()
            }
            else -> {}
        }
    }

    fun executeSaveGroup()
    {
        var selectedGroup = mViewModel.observableSelectedPersonRelationshpInfos
        if(selectedGroup.size != 0)
        {
            SharedPreferencesLib(this).saveGroupData(
                    StringLib.getGroupName(mBinding.groupNameEditText.text.toString()),
                    mBinding.contactTermSpinner.selectedItem.toString(),
                    selectedGroup)
        }
    }
}
