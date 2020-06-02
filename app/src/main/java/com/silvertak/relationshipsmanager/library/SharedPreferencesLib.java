package com.silvertak.relationshipsmanager.library;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.databinding.ObservableArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.silvertak.relationshipsmanager.vo.PersonRelationshipInfo;
import com.silvertak.relationshipsmanager.define.JsonStringDefine;

public class SharedPreferencesLib {
    public static SharedPreferencesLib singleTon;
    public static SharedPreferencesLib getInstance(@Nullable Context context)
    {
        if(context != null)
        {
            singleTon = new SharedPreferencesLib(context);
        }

        return singleTon;
    }

    public SharedPreferences objSharedPreferences;

    public SharedPreferencesLib(Context context)
    {
        objSharedPreferences = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
    }

    public void saveGroupData(String strGroupId, String strGroupName, String strGroupContactTerm, ObservableArrayList<PersonRelationshipInfo> relationshipInfos)
    {
        JsonArray jsonArray = new JsonArray();

        if(!StringLib.isEmpty(load(JsonStringDefine.GROUP.ARRAY)))
            jsonArray = (JsonArray)new JsonParser().parse(load(JsonStringDefine.GROUP.ARRAY));

        JsonArray groupDataArray = new JsonArray();
        for(PersonRelationshipInfo info : relationshipInfos)
        {
            JsonObject jsonObject = new JsonObject();

            jsonObject.addProperty(JsonStringDefine.CONTACT.ID, info.getContactInfo().getId());
            //jsonObject.addProperty(JsonStringDefine.CONTACT.EMAIL, info.getContactInfo().getEmail());
            //jsonObject.addProperty(JsonStringDefine.CONTACT.NAME, info.getContactInfo().getName());
            //jsonObject.addProperty(JsonStringDefine.CONTACT.PHONE_NUMBER, info.getContactInfo().getPhoneNumber());
            //jsonObject.addProperty(JsonStringDefine.CONTACT.PHOTO_ID, info.getContactInfo().getPhotoId());

            groupDataArray.add(jsonObject);
        }

        JsonObject groupObject = new JsonObject();

        groupObject.addProperty(JsonStringDefine.GROUP.ID, strGroupId);
        groupObject.addProperty(JsonStringDefine.GROUP.NAME, strGroupName);
        groupObject.addProperty(JsonStringDefine.GROUP.TERM, strGroupContactTerm);
        groupObject.addProperty(JsonStringDefine.GROUP.DATA, groupDataArray.toString());

        jsonArray.add(groupObject);

        save(JsonStringDefine.GROUP.ARRAY, jsonArray.toString());
    }

    public String loadGroupData()
    {
        return load(JsonStringDefine.GROUP.ARRAY);
    }

    public void deleteGroupData(String strGroupId)
    {
        String jsonString = loadGroupData();

        if(StringLib.isEmpty(jsonString))
            return;

        JsonArray jsonArray = (JsonArray)new JsonParser().parse(jsonString);

        for(JsonElement element : jsonArray)
        {
            JsonObject jsonObject = (JsonObject) element;

            if(jsonObject.has(JsonStringDefine.GROUP.ID))
            {
                if(jsonObject.get(JsonStringDefine.GROUP.ID).getAsString().equals(strGroupId))
                {
                    jsonArray.remove(element);
                    break;
                }
            }
        }

        save(JsonStringDefine.GROUP.ARRAY, jsonArray.toString());
    }

    public void deleteAllGroupData()
    {
        save(JsonStringDefine.GROUP.ARRAY, "[]");
    }

    public void save(String strKey, String strDataValue)
    {
        SharedPreferences.Editor editor = objSharedPreferences.edit();
        editor.putString(strKey, strDataValue);
        editor.commit();

        Log.i("SharedPreferencesLib", "SaveGroup : " + strDataValue);
    }

    public String load(String strKey)
    {
        String strSavedData = objSharedPreferences.getString(strKey, "");
        return strSavedData;
    }

    public String getString(String strKey, String strDefaultValue)
    {
        return objSharedPreferences.getString(strKey, strDefaultValue);
    }

    public void putString(String strKey, String strValue)
    {
        SharedPreferences.Editor objEditor = objSharedPreferences.edit();
        objEditor.putString(strKey, strValue);
        objEditor.commit();
    }

    public boolean getBoolean(String strKey)
    {
        return objSharedPreferences.getBoolean(strKey, false);
    }

    public void putBoolean(String strKey, boolean bValue)
    {
        SharedPreferences.Editor objEditor = objSharedPreferences.edit();
        objEditor.putBoolean(strKey, bValue);
        objEditor.commit();
    }
}
