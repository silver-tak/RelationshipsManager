package com.silvertak.relationshipsmanager.viewmodel;

import androidx.databinding.ObservableArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.silvertak.relationshipsmanager.data.RelationshipGroupInfo;
import com.silvertak.relationshipsmanager.define.JsonStringDefine;

import java.util.ArrayList;

public class GroupModifyViewModel extends BaseViewModel {

    private ObservableArrayList<RelationshipGroupInfo> relationshipGroupInfos = new ObservableArrayList<>();

    public GroupModifyViewModel(){}

    public ObservableArrayList<RelationshipGroupInfo> getRelationshipGroupInfos()
    {
        return this.relationshipGroupInfos;
    }

    public void loadGroupInfoData(String strGroupInfoJson)
    {
        relationshipGroupInfos.clear();
        JsonArray jsonElements = (JsonArray)new JsonParser().parse(strGroupInfoJson);

        for(JsonElement element : jsonElements)
        {
            JsonObject jsonObject = (JsonObject)element;

            String strGroupId = "";
            String strGroupName = "";
            String strGroupTerm = "";
            String strGroupData = "";

            if(jsonObject.has(JsonStringDefine.GROUP.ID))
                strGroupId = jsonObject.get(JsonStringDefine.GROUP.ID).getAsString();
            if(jsonObject.has(JsonStringDefine.GROUP.NAME))
                strGroupName = jsonObject.get(JsonStringDefine.GROUP.NAME).getAsString();
            if(jsonObject.has(JsonStringDefine.GROUP.TERM))
                strGroupTerm = jsonObject.get(JsonStringDefine.GROUP.TERM).getAsString();
            if(jsonObject.has(JsonStringDefine.GROUP.DATA))
                strGroupData = jsonObject.get(JsonStringDefine.GROUP.DATA).getAsString();

            RelationshipGroupInfo relationshipInfo = new RelationshipGroupInfo(strGroupId, strGroupName, strGroupTerm, strGroupData, personRelationshipInfos);
            relationshipGroupInfos.add(relationshipInfo);
        }
    }
}
