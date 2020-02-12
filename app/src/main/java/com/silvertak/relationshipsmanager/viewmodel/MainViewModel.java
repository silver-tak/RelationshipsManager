package com.silvertak.relationshipsmanager.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private MutableLiveData<Integer> relationshipScore = new MutableLiveData<>();

    public void setRelationshipScore(int nValue)
    {
        relationshipScore.setValue(nValue);
    }

    public MutableLiveData<Integer> getRelationshipScore() {
        if(relationshipScore == null)
            relationshipScore.setValue(0);

        return relationshipScore;
    }
}
