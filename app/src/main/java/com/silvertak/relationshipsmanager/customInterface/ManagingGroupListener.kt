package com.silvertak.relationshipsmanager.customInterface

import com.silvertak.relationshipsmanager.vo.RelationshipGroupInfo

interface ManagingGroupListener {
    fun onInfoClick(info: RelationshipGroupInfo?)
    fun onDeleteClick(info: RelationshipGroupInfo?)
    fun onModifyClick(info: RelationshipGroupInfo?)
}