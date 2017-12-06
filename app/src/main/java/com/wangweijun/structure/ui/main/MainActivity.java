package com.wangweijun.structure.ui.main;

import android.os.Bundle;
import android.util.Log;

import com.wangweijun.structure.R;
import com.wangweijun.structure.data.DataManager;
import com.wangweijun.structure.ui.base.BaseActivity;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    @Inject
    DataManager mDataManager;

    @Inject
    DataManager mDataManager2;

    @Inject
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityComponent().inject(this);

        Log.i("wang", "mDataManager:"+mDataManager);
        Log.i("wang", "the same :"+(mDataManager==mDataManager2));

        mainPresenter.getRankApps();
    }
}
