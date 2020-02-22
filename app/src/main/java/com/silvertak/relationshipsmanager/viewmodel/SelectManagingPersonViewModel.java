package com.silvertak.relationshipsmanager.viewmodel;

import android.os.Bundle;
import android.util.Log;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import com.silvertak.relationshipsmanager.data.PersonRelationshipInfo;

public class SelectManagingPersonViewModel extends BaseViewModel {

    private ObservableArrayList<PersonRelationshipInfo> observablePersonRelationshpInfos = new ObservableArrayList<>();
    private ObservableArrayList<PersonRelationshipInfo> observableSelectedPersonRelationshpInfos = new ObservableArrayList<>();

    @Override
    public void setDataArrayList(Bundle bundle) {
        super.setDataArrayList(bundle);

        for(PersonRelationshipInfo info : personRelationshipInfos)
            observablePersonRelationshpInfos.add(info);
    }

    public ObservableArrayList<PersonRelationshipInfo> getObservablePersonRelationshpInfos()
    {
        return this.observablePersonRelationshpInfos;
    }

    public ObservableArrayList<PersonRelationshipInfo> getObservableSelectedPersonRelationshpInfos()
    {
        observableSelectedPersonRelationshpInfos.addOnListChangedCallback(new ObservableList.OnListChangedCallback<ObservableList<PersonRelationshipInfo>>() {
            @Override
            public void onChanged(ObservableList<PersonRelationshipInfo> sender) {
                Log.i("observer", sender.size() + "개");
            }

            @Override
            public void onItemRangeChanged(ObservableList<PersonRelationshipInfo> sender, int positionStart, int itemCount) {
                Log.i("observer", sender.size() + "개");
            }

            @Override
            public void onItemRangeInserted(ObservableList<PersonRelationshipInfo> sender, int positionStart, int itemCount) {
                Log.i("observer", sender.size() + "개");

            }

            @Override
            public void onItemRangeMoved(ObservableList<PersonRelationshipInfo> sender, int fromPosition, int toPosition, int itemCount) {
                Log.i("observer", sender.size() + "개");

            }

            @Override
            public void onItemRangeRemoved(ObservableList<PersonRelationshipInfo> sender, int positionStart, int itemCount) {
                Log.i("observer", sender.size() + "개");

            }
        });

        return this.observableSelectedPersonRelationshpInfos;
    }
}
