package com.silvertak.relationshipsmanager.viewmodel;

import android.os.Bundle;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.silvertak.relationshipsmanager.data.CallLogInfo;
import com.silvertak.relationshipsmanager.data.ContactInfo;

import java.util.ArrayList;

public class StatusViewModel extends ViewModel {

    private MutableLiveData<Integer> relationshipScore = new MutableLiveData<>();
    private ObservableArrayList<ContactInfo> mostContactRankingList = new ObservableArrayList<>();
    private ObservableArrayList<ContactInfo> needContactRankingList = new ObservableArrayList<>();
    private ArrayList<ContactInfo> contactInfos = new ArrayList<>();
    private ArrayList<CallLogInfo> callLogInfos = new ArrayList<>();

    public void setDataArrayList(Bundle bundle)
    {
        if(bundle.getParcelableArrayList("contactInfos") != null)
            contactInfos = bundle.getParcelableArrayList("contactInfos");
        if(bundle.getParcelableArrayList("callLogInfos") != null)
            callLogInfos = bundle.getParcelableArrayList("callLogInfos");
    }

    public void prepareVisibleData()
    {
        setRelationshipScore(88);
        mostContactRankingList.add(new ContactInfo("홍길동", "2020. 02. 15", "010-1111-1111", "", ""));
        mostContactRankingList.add(new ContactInfo("홍길동", "2020. 02. 15", "010-2222-2222", "", ""));
        mostContactRankingList.add(new ContactInfo("홍길동", "2020. 02. 15", "010-3333-3333", "", ""));
        mostContactRankingList.add(new ContactInfo("홍길동", "2020. 02. 15", "010-4444-4444", "", ""));
        mostContactRankingList.add(new ContactInfo("홍길동", "2020. 02. 15", "010-5555-5555", "", ""));
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

    public ObservableArrayList<ContactInfo> getMostContactRankingList() {
        return mostContactRankingList;
    }

    public ObservableArrayList<ContactInfo> getNeedContactRankingList() {
        return needContactRankingList;
    }
}
