package com.silvertak.relationshipsmanager.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.silvertak.relationshipsmanager.R;
import com.silvertak.relationshipsmanager.fragment.base.BaseFragment;

public class GroupModifyFragment extends BaseFragment {

    public GroupModifyFragment() {
        // Required empty public constructor
    }

    public static GroupModifyFragment newInstance(Bundle args) {
        GroupModifyFragment fragment = new GroupModifyFragment();
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
        return inflater.inflate(R.layout.fragment_group_modify, container, false);
    }
}
