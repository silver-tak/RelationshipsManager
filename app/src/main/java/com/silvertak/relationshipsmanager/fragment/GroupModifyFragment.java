package com.silvertak.relationshipsmanager.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.silvertak.relationshipsmanager.R;
import com.silvertak.relationshipsmanager.databinding.FragmentGroupModifyBinding;
import com.silvertak.relationshipsmanager.fragment.base.BaseFragment;
import com.silvertak.relationshipsmanager.viewmodel.GroupModifyViewModel;

public class GroupModifyFragment extends BaseFragment {

    private GroupModifyViewModel groupModifyViewModel;
    private FragmentGroupModifyBinding mBinding;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        groupModifyViewModel = ViewModelProviders.of(this).get(GroupModifyViewModel.class);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_group_modify, container, false);
        mBinding.setGroupModifyViewModel(groupModifyViewModel);

        return mBinding.getRoot();
    }
}
