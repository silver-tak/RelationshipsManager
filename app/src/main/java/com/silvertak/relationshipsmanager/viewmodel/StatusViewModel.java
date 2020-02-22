package com.silvertak.relationshipsmanager.viewmodel;

import android.os.Bundle;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.silvertak.relationshipsmanager.data.CallLogInfo;
import com.silvertak.relationshipsmanager.data.ContactInfo;
import com.silvertak.relationshipsmanager.data.PersonRelationshipArray;
import com.silvertak.relationshipsmanager.data.PersonRelationshipInfo;
import com.silvertak.relationshipsmanager.library.DataCombineLib;

import java.util.ArrayList;

public class StatusViewModel extends BaseViewModel {

    private MutableLiveData<Integer> relationshipScore = new MutableLiveData<>();
    private ObservableArrayList<PersonRelationshipInfo> mostContactRankingList = new ObservableArrayList<>();
    private ObservableArrayList<PersonRelationshipInfo> needContactRankingList = new ObservableArrayList<>();

    public void prepareVisibleData()
    {
        setRelationshipScore(88);
        mostContactRankingList.clear();

        ArrayList<PersonRelationshipInfo> mostContactRankingArray = personRelationshipInfos.getMostContactRanking();
        for(PersonRelationshipInfo info : mostContactRankingArray)
            mostContactRankingList.add(info);
    }

    public void setRelationshipScore(int nValue)
    {
        relationshipScore.setValue(nValue);
    }

    public MutableLiveData<Integer> getRelationshipScore() {
        if(relationshipScore == null)
            relationshipScore.setValue(0);

        return relationshipScore;
    }

    public ObservableArrayList<PersonRelationshipInfo> getMostContactRankingList() {
        return mostContactRankingList;
    }

    public ObservableArrayList<PersonRelationshipInfo> getNeedContactRankingList() {
        return needContactRankingList;
    }
}
