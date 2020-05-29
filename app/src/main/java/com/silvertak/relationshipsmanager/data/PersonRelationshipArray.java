package com.silvertak.relationshipsmanager.data;

import com.silvertak.relationshipsmanager.library.StringLib;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Stream;

public class PersonRelationshipArray extends ArrayList<PersonRelationshipInfo> {

    public PersonRelationshipArray(){}

    public PersonRelationshipArray(ArrayList<PersonRelationshipInfo> arrayList){
        for(PersonRelationshipInfo info : arrayList) this.add(info);
    }

    /**
     * 가장 많이 연락한 5명을 ArrayList 형태로 리턴한다.
     * @return
     */
    public ArrayList<PersonRelationshipInfo> getMostContactRanking()
    {
        Collections.sort(this);

        ArrayList<PersonRelationshipInfo> resultArray = new ArrayList<>();

        for(PersonRelationshipInfo info : this)
        {
            resultArray.add(info);
            //if(resultArray.size() == 5) break;
        }

        return resultArray;
    }

    public PersonRelationshipInfo getInfoById(String id)
    {
        for(PersonRelationshipInfo info : this)
        {
            if(!StringLib.isEmpty(info.getContactInfo().getId()) && info.getContactInfo().getId().equals(id))
            {
                return info;
            }
        }

        return null;
    }
}
