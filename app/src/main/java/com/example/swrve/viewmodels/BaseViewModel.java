package com.example.swrve.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.swrve.database.AppDatabase;

public class BaseViewModel extends AndroidViewModel {

    public final AppDatabase mDb;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        mDb = AppDatabase.getInstance(application);
    }
}
