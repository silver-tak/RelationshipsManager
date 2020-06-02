package com.silvertak.relationshipsmanager.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.silvertak.relationshipsmanager.R
import com.silvertak.relationshipsmanager.view.fragment.base.BaseFragment

class SettingFragment : BaseFragment() {
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? { // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    companion object {
        fun newInstance(args: Bundle?): SettingFragment {
            val fragment = SettingFragment()
            fragment.arguments = args
            return fragment
        }
    }
}