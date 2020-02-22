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
import com.silvertak.relationshipsmanager.viewmodel.SelectManagingPersonViewModel

class SelectManagingPersonActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mViewModel: SelectManagingPersonViewModel
    private lateinit var mBinding: ActivitySelectManagingPersonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_managing_person)

        mViewModel = ViewModelProviders.of(this).get(SelectManagingPersonViewModel::class.java)
        mViewModel.setDataArrayList(intent.getBundleExtra(StringDefine.KEY_DEFAULT_BUNDLE_KEY))
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_select_managing_person)
        mBinding.selectManagingPersonActivity = this
        mBinding.selectManagingPersonViewModel = mViewModel

        mBinding.searchEditTextView.mSearchTextListener = SearchTextListener{strValue: String? -> Log.i("SelectManagingPerson", "텍스트감지 : $strValue")}

        mBinding.contactTermSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, resources.getStringArray(R.array.contactTermArray))
        mBinding.contactTermSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            }
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        mBinding.contactTermSpinner.setSelection(3, true)
        mBinding.selectablePersonList.adapter = SelectablePersonListAdapter()

        mBinding.selectManagingPersonViewModel = mViewModel
        mBinding.lifecycleOwner = this
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
                finish()
            }
            else -> {}
        }
    }
}
