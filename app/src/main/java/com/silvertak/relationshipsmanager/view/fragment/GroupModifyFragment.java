package com.silvertak.relationshipsmanager.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.silvertak.relationshipsmanager.R;
import com.silvertak.relationshipsmanager.databinding.FragmentGroupModifyBinding;
import com.silvertak.relationshipsmanager.view.SelectManagingPersonActivity;
import com.silvertak.relationshipsmanager.view.fragment.base.BaseFragment;
import com.silvertak.relationshipsmanager.viewmodel.GroupModifyViewModel;

public class GroupModifyFragment extends BaseFragment implements View.OnClickListener{

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
        mBinding.setGroupModifyFragment(this);
        mBinding.addGroupBtn.setOnClickListener(this);
        return mBinding.getRoot();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.addGroupBtn :
                startActivityForResult(new Intent(getActivity(), SelectManagingPersonActivity.class), 1000); break;
        }
    }
}
