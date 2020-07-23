package com.dailyexpences.demo.ui.accounts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AccountsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AccountsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is accounts fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}