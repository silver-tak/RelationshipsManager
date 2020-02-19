package com.silvertak.relationshipsmanager.viewmodel;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.silvertak.relationshipsmanager.data.RelationshipGroupArray;

public class GroupModifyViewModel extends ViewModel {
    private MutableLiveData<Integer> relationshipScore = new MutableLiveData<>();
    private ObservableArrayList<RelationshipGroupArray> groupList = new ObservableArrayList<>();



}
