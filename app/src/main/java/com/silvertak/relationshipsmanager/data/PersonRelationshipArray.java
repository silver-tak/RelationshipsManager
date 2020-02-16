package com.silvertak.relationshipsmanager.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
}
