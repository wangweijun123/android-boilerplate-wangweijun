package com.wangweijun.structure.ui.game;

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
 * Created by wangweijun on 2017/12/9.
 */

public class GamePresenter extends BasePresenter<GameMvpView> {

    DataManager mDataManager;

    @Inject
    public GamePresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    public void getRankApps() {
        checkViewAttached();
        mDataManager.getRankApps("40", "20", "RANK_HOT")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<IResponse<RankListModel>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        getMvpView().showLoading();
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
                        if (list != null && list.size() > 0) {
                            getMvpView().showDataLoadSuccess(list);
                        } else {
                            getMvpView().showEmptyUI();
                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("wang", "onError :");
                        getMvpView().hideLoading();
                        getMvpView().showErrorUI();
                    }

                    @Override
                    public void onComplete() {
                        getMvpView().hideLoading();
                    }
                });
    }

    public void refresh() {
        getMvpView().hideErrorUI();
        getRankApps();
    }
}
