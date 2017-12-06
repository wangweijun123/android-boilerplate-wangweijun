package com.wangweijun.structure.data;

import com.wangweijun.structure.data.model.IResponse;
import com.wangweijun.structure.data.model.RankListModel;
import com.wangweijun.structure.data.remote.StoreService;

import io.reactivex.Observable;

/**
 * Created by wangweijun1 on 2017/12/6.
 */

public class DataManager {

    StoreService mStoreService;

    public DataManager(StoreService storeService) {
        mStoreService = storeService;
    }


    public Observable<IResponse<RankListModel>> getRankApps() {
        return mStoreService.getRankApps("1", "5", "RANK_HOT");
    }
}
