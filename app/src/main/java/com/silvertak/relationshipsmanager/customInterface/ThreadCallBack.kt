package com.silvertak.relationshipsmanager.customInterface

import com.silvertak.relationshipsmanager.vo.CallLogVO
import com.silvertak.relationshipsmanager.vo.ContactInfoVO
import com.silvertak.relationshipsmanager.vo.PersonRelationshipInfo
import java.util.*

interface ThreadCallBack {
    fun onContactMappingComplete(contactInfos: ArrayList<ContactInfoVO?>?)
    fun onCallLogMappingComplete(callLogInfos: ArrayList<CallLogVO?>?)
    fun onPersonRelationshipMappingComplete(personRelationshipInfos: ArrayList<PersonRelationshipInfo?>?)
}