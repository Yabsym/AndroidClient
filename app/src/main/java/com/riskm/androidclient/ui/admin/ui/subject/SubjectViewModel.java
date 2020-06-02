package com.riskm.androidclient.ui.admin.ui.subject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SubjectViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SubjectViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
