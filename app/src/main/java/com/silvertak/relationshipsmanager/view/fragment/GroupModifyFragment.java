package com.silvertak.relationshipsmanager.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.silvertak.relationshipsmanager.R;
import com.silvertak.relationshipsmanager.adapter.GroupListAdapter;
import com.silvertak.relationshipsmanager.customInterface.ManagingGroupListener;
import com.silvertak.relationshipsmanager.customView.CustomDIalog;
import com.silvertak.relationshipsmanager.data.PersonRelationshipInfo;
import com.silvertak.relationshipsmanager.data.RelationshipGroupInfo;
import com.silvertak.relationshipsmanager.databinding.FragmentGroupModifyBinding;
import com.silvertak.relationshipsmanager.define.StringDefine;
import com.silvertak.relationshipsmanager.library.SharedPreferencesLib;
import com.silvertak.relationshipsmanager.library.StringLib;
import com.silvertak.relationshipsmanager.view.SelectManagingPersonActivity;
import com.silvertak.relationshipsmanager.view.fragment.base.BaseFragment;
import com.silvertak.relationshipsmanager.viewmodel.GroupModifyViewModel;

import java.util.ArrayList;

public class GroupModifyFragment extends BaseFragment implements View.OnClickListener{

    private GroupModifyViewModel groupModifyViewModel;
    private FragmentGroupModifyBinding mBinding;
    private GroupListAdapter groupListAdapter;

    public GroupModifyFragment() {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        groupModifyViewModel = ViewModelProviders.of(this).get(GroupModifyViewModel.class);
        groupModifyViewModel.setDataArrayList(getArguments());

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_group_modify, container, false);
        mBinding.setGroupModifyViewModel(groupModifyViewModel);
        mBinding.setGroupModifyFragment(this);
        mBinding.addGroupBtn.setOnClickListener(this);

        groupListAdapter = new GroupListAdapter();
        groupListAdapter.setManagingGroupListener(new ManagingGroupListener() {
            @Override
            public void onInfoClick(RelationshipGroupInfo info) {
                Log.i("ManagingGrroupListener", "onInfoClick called, " + info.getStrGroupName() + ", " + info.size());
                modifyGroupData(info.getStrGroupName(), info.getStrGroupId());
            }

            @Override
            public void onDeleteClick(RelationshipGroupInfo info) {
                Log.i("ManagingGrroupListener", "onDeleteClick called, " + info.getStrGroupName() + ", " + info.size());
                deleteGroupData(info.getStrGroupName(), info.getStrGroupId());
            }

            @Override
            public void onModifyClick(RelationshipGroupInfo info) {
                Log.i("ManagingGrroupListener", "onModifyClick called, " + info.getStrGroupName() + ", " + info.size());
            }
        });
        mBinding.groupListRecyclerView.setAdapter(groupListAdapter);

        return mBinding.getRoot();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        loadGroupData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.addGroupBtn :
                Intent intent = new Intent(getActivity(), SelectManagingPersonActivity.class);
                intent.putExtra(StringDefine.KEY_DEFAULT_BUNDLE_KEY, getOriginalPersonRelationshipInfoBundle());
                startSelectManagingPersonActivity(intent, 1000);
                break;
        }
    }

    private Bundle getOriginalPersonRelationshipInfoBundle()
    {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(StringDefine.KEY_PERSON_RELATIONSHIP_INFO_ARRAY, groupModifyViewModel.getPersonRelationshipInfos());
        return bundle;
    }

    private void startSelectManagingPersonActivity(Intent intent, int nRequestCode)
    {
        startActivityForResult(intent, nRequestCode);
        getActivity().overridePendingTransition(R.anim.scale_zoom_in,0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loadGroupData();
    }

    private void loadGroupData()
    {
        String strGroupDataJson = SharedPreferencesLib.getInstance(getContext()).loadGroupData();
        if(!StringLib.isEmpty(strGroupDataJson))
        {
            groupModifyViewModel.loadGroupInfoData(SharedPreferencesLib.getInstance(getContext()).loadGroupData());
            groupListAdapter.setGroupInfos(groupModifyViewModel.getRelationshipGroupInfos());
        }
    }

    private void deleteGroupData(String strGroupName, final String strGroupId)
    {
        CustomDIalog customDIalog = new CustomDIalog(getActivity());
        customDIalog.showYesOrNoDialog(String.format(getString(R.string.delete_msg), strGroupName), view -> {
            SharedPreferencesLib.getInstance(getContext()).deleteGroupData(strGroupId);
            loadGroupData();
        });
    }

    private void modifyGroupData(String strGroupName, final String strGroupId)
    {

    }
}
