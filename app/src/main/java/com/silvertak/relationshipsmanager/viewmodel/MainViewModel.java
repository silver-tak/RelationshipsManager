package com.silvertak.relationshipsmanager.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private LiveData<Integer> nTwoWeeksLaterCount = 0;

    public int getTwoWeeksLaterCount()
    {
        return nTwoWeeksLaterCount;
    }
}
