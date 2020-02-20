package com.silvertak.relationshipsmanager.view.fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.silvertak.relationshipsmanager.R;
import com.silvertak.relationshipsmanager.view.fragment.base.BaseFragment;

public class SettingFragment extends BaseFragment {

    public SettingFragment() {
        // Required empty public constructor
    }

    public static SettingFragment newInstance(Bundle args) {
        SettingFragment fragment = new SettingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }
}
