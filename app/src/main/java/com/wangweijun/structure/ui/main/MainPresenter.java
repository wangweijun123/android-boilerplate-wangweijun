package com.wangweijun.structure.ui.main;

import android.util.Log;

import com.wangweijun.structure.data.DataManager;
import com.wangweijun.structure.data.model.BaseModel;
import com.wangweijun.structure.data.model.IResponse;
import com.wangweijun.structure.data.model.RankListMiddle;
import com.wangweijun.structure.data.model.RankListModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wangweijun1 on 2017/12/6.
 */

public class MainPresenter {

    DataManager mDataManager;

    @Inject
    public MainPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    public void getRankApps() {
        mDataManager.getRankApps()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<IResponse<RankListModel>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull IResponse<RankListModel> rankListModelIResponse) {
                        RankListModel rankListModel = rankListModelIResponse.getEntity();
                        String code =  rankListModelIResponse.getCode();
                        Log.i("wang", "onNext code:"+code);
                        RankListMiddle rankListMiddle = rankListModel.ranklist.get(0);
                        List<BaseModel> list =  rankListMiddle.items;
                        for(BaseModel baseModel :  list) {
                            Log.i("wang", "baseModel:"+baseModel);
                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
