package com.silvertak.relationshipsmanager.vo

import android.util.Log
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.silvertak.relationshipsmanager.define.JsonStringDefine
import io.reactivex.rxjava3.core.Observable
import java.util.function.Function

class RelationshipGroupInfo(groupId: String, groupName: String, groupContactTerm: String, groupDataJsonString: String, personRelationshipInfos: PersonRelationshipArray) : ArrayList<PersonRelationshipInfo>() {

    var strGroupId: String = groupId
    var strGroupName: String = groupName
    var strGroupContactTerm: String = groupContactTerm

    init {
        setArrayData(groupDataJsonString, personRelationshipInfos)
    }

    private fun setArrayData(groupDataJsonString: String, personRelationshipInfos: PersonRelationshipArray) : Unit
    {
        (JsonParser().parse(groupDataJsonString) as JsonArray).run {
            Observable.fromIterable(this)
                    .subscribe { item ->
                        (item as JsonObject).get(JsonStringDefine.CONTACT.ID).asString?.let {
                            this@RelationshipGroupInfo.add(personRelationshipInfos.getInfoById(it))
                        }
                    }
        }
    }
}