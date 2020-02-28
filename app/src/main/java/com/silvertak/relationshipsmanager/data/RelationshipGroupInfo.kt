package com.silvertak.relationshipsmanager.data

import android.util.Log
import androidx.databinding.ObservableArrayList
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.silvertak.relationshipsmanager.define.JsonStringDefine

class RelationshipGroupInfo: ArrayList<PersonRelationshipInfo> {

    var strGroupId: String = ""
    var strGroupName: String = ""
    var strGroupContactTerm: String = ""

    constructor()

    constructor(groupId: String, groupName: String, groupContactTerm: String, groupDataJsonString: String, personRelationshipInfos: PersonRelationshipArray)
    {
        strGroupId = groupId
        strGroupName = groupName
        strGroupContactTerm = groupContactTerm
        setArrayData(groupDataJsonString, personRelationshipInfos)
    }

    constructor(groupId: String, groupName: String, groupContactTerm: String, groupData: ArrayList<PersonRelationshipInfo>)
    {
        strGroupId = groupId
        strGroupName = groupName
        strGroupContactTerm = groupContactTerm
        setArrayData(groupData)
    }

    fun setArrayData(groupDataJsonString: String, personRelationshipInfos: PersonRelationshipArray) : Unit
    {
        val jsonArray: JsonArray = JsonParser().parse(groupDataJsonString) as JsonArray
        for(item in jsonArray)
        {
            var id = if((item as JsonObject).has(JsonStringDefine.CONTACT.ID)) item.get(JsonStringDefine.CONTACT.ID).asString else ""
            this.add(personRelationshipInfos.getInfoById(id))
        }
    }

    override fun add(element: PersonRelationshipInfo): Boolean {
        Log.i("setArrayData", element.contactInfo.name + " - " + element.contactInfo.phoneNumber + " added")
        return super.add(element)
    }

    fun setArrayData(groupData: ArrayList<PersonRelationshipInfo>)
    {
        for(info in groupData) this.add(info)
    }
}