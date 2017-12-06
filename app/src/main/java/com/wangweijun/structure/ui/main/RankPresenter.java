package com.wangweijun.structure.ui.main;

import android.util.Log;

import com.wangweijun.structure.data.DataManager;
import com.wangweijun.structure.data.model.BaseModel;
import com.wangweijun.structure.data.model.IResponse;
import com.wangweijun.structure.data.model.RankListMiddle;
import com.wangweijun.structure.data.model.RankListModel;
import com.wangweijun.structure.ui.base.BasePresenter;

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

public class RankPresenter extends BasePresenter<RankMvpView>{

    DataManager mDataManager;

    @Inject
    public RankPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(RankMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
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
                        String code = rankListModelIResponse.getCode();
                        Log.i("wang", "onNext code:" + code);
                        RankListMiddle rankListMiddle = rankListModel.ranklist.get(0);
                        List<BaseModel> list = rankListMiddle.items;
                        for (BaseModel baseModel : list) {
                            Log.i("wang", "baseModel:" + baseModel);
                        }
                        getMvpView().showDataLoadSuccess(list);
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
