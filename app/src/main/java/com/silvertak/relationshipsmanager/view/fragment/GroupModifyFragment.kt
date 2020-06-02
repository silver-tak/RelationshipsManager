package com.silvertak.relationshipsmanager.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.silvertak.relationshipsmanager.R
import com.silvertak.relationshipsmanager.adapter.GroupListAdapter
import com.silvertak.relationshipsmanager.customInterface.ManagingGroupListener
import com.silvertak.relationshipsmanager.customView.CustomDIalog
import com.silvertak.relationshipsmanager.databinding.FragmentGroupModifyBinding
import com.silvertak.relationshipsmanager.define.StringDefine
import com.silvertak.relationshipsmanager.library.SharedPreferencesLib
import com.silvertak.relationshipsmanager.library.StringLib
import com.silvertak.relationshipsmanager.view.SelectManagingPersonActivity
import com.silvertak.relationshipsmanager.view.fragment.base.BaseFragment
import com.silvertak.relationshipsmanager.viewmodel.GroupModifyViewModel
import com.silvertak.relationshipsmanager.vo.RelationshipGroupInfo

class GroupModifyFragment : BaseFragment(), View.OnClickListener {
    private lateinit var groupModifyViewModel: GroupModifyViewModel
    private lateinit var mBinding: FragmentGroupModifyBinding
    private lateinit var groupListAdapter: GroupListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        groupModifyViewModel = ViewModelProviders.of(this).get(GroupModifyViewModel::class.java)
                .apply {
                    setDataArrayList(arguments)
                }
        mBinding = DataBindingUtil.inflate<FragmentGroupModifyBinding>(inflater, R.layout.fragment_group_modify, container, false)
                .apply {
                    this.groupModifyViewModel = this@GroupModifyFragment.groupModifyViewModel
                    groupModifyFragment = this@GroupModifyFragment
                    addGroupBtn.setOnClickListener(this@GroupModifyFragment)
                }
                .apply {
                    groupListRecyclerView.adapter = GroupListAdapter()
                            .apply {
                                setManagingGroupListener(object : ManagingGroupListener{
                                    override fun onInfoClick(info: RelationshipGroupInfo?) {
                                        Log.i("ManagingGrroupListener", "onInfoClick called, " + info!!.strGroupName + ", " + info.size)
                                        modifyGroupData(info.strGroupName, info.strGroupId)
                                    }
                                    override fun onDeleteClick(info: RelationshipGroupInfo?) {
                                        Log.i("ManagingGrroupListener", "onDeleteClick called, " + info!!.strGroupName + ", " + info.size)
                                        deleteGroupData(info.strGroupName, info.strGroupId)
                                    }
                                    override fun onModifyClick(info: RelationshipGroupInfo?) {
                                        Log.i("ManagingGrroupListener", "onModifyClick called, " + info!!.strGroupName + ", " + info.size)
                                    }
                                })

                                this@GroupModifyFragment.groupListAdapter = this
                            }
                }
        return mBinding.root
    }

    override fun onResume() {
        super.onResume()
        loadGroupData()
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.addGroupBtn -> {
                Intent(activity, SelectManagingPersonActivity::class.java).run {
                    putExtra(StringDefine.KEY_DEFAULT_BUNDLE_KEY, originalPersonRelationshipInfoBundle)
                    startSelectManagingPersonActivity(this, 1000)
                }
            }
        }
    }

    private val originalPersonRelationshipInfoBundle: Bundle
        get() {
            Bundle().apply {
                putParcelableArrayList(StringDefine.KEY_PERSON_RELATIONSHIP_INFO_ARRAY, groupModifyViewModel!!.personRelationshipInfos)
                return this
            }
        }

    private fun startSelectManagingPersonActivity(intent: Intent, nRequestCode: Int) {
        startActivityForResult(intent, nRequestCode)
        activity!!.overridePendingTransition(R.anim.scale_zoom_in, 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        loadGroupData()
    }

    private fun loadGroupData() = SharedPreferencesLib.getInstance(context).loadGroupData()?.let {
            if(it.isNotEmpty())
            {
                groupModifyViewModel.loadGroupInfoData(SharedPreferencesLib.getInstance(context).loadGroupData())
                groupListAdapter.setGroupInfos(groupModifyViewModel.relationshipGroupInfos)
            }
        }


    private fun deleteGroupData(strGroupName: String, strGroupId: String) {
        CustomDIalog(activity)
        .run {
            showYesOrNoDialog(String.format(getString(R.string.delete_msg), strGroupName)) {
                SharedPreferencesLib.getInstance(context).deleteGroupData(strGroupId)
                loadGroupData()
            }
        }
    }

    private fun modifyGroupData(strGroupName: String, strGroupId: String) {}

    companion object {
        fun newInstance(args: Bundle?): GroupModifyFragment {
            val fragment = GroupModifyFragment()
            fragment.arguments = args
            return fragment
        }
    }
}