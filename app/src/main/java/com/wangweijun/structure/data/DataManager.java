package com.wangweijun.structure.data;

import android.util.Log;

import com.wangweijun.structure.data.local.PreferencesHelper;
import com.wangweijun.structure.data.model.AppDetailsModel;
import com.wangweijun.structure.data.model.IResponse;
import com.wangweijun.structure.data.model.RankListModel;
import com.wangweijun.structure.data.remote.StoreService;

import io.reactivex.Observable;

/**
 * Created by wangweijun1 on 2017/12/6.
 */

public class DataManager {

    private PreferencesHelper mPreferencesHelper;

    private StoreService mStoreService;

    public DataManager(StoreService storeService, PreferencesHelper preferencesHelper) {
        mStoreService = storeService;
        mPreferencesHelper = preferencesHelper;
    }

    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }

    public Observable<IResponse<RankListModel>> getRankApps() {
        return mStoreService.getRankApps("1", "5", "RANK_HOT");
    }

    public Observable<IResponse<AppDetailsModel>> getAppDetail(String packagename) {
        Log.i("wang", "getAppDetail mStoreService:"+mStoreService);
        return mStoreService.getAppDetail(packagename);
    }

}
