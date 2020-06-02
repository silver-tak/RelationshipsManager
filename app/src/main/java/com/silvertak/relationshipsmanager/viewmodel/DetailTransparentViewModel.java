package com.silvertak.relationshipsmanager.viewmodel;

import androidx.lifecycle.ViewModel;

import com.silvertak.relationshipsmanager.vo.PersonRelationshipInfo;

public class DetailTransparentViewModel extends ViewModel {

    private PersonRelationshipInfo personRelationshipInfo;

    public void setPersonRelationshipInfo(PersonRelationshipInfo info)
    {
        this.personRelationshipInfo = info;
    }

    public PersonRelationshipInfo getPersonRelationshipInfo()
    {
        return this.personRelationshipInfo;
    }
}
