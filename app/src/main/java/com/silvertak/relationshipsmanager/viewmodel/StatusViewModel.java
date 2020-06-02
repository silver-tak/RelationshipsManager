package com.silvertak.relationshipsmanager.viewmodel;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.MutableLiveData;

import com.silvertak.relationshipsmanager.vo.PersonRelationshipInfo;

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
        mostContactRankingList.addAll(mostContactRankingArray);
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
