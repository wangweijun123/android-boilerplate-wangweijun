package com.wangweijun.structure.ui.home;

import android.util.Log;

import com.wangweijun.structure.data.DataManager;
import com.wangweijun.structure.data.model.BaseModel;
import com.wangweijun.structure.data.model.HomePageModel;
import com.wangweijun.structure.data.model.IResponse;
import com.wangweijun.structure.ui.base.BasePresenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wangweijun on 2017/12/9.
 */

public class HomePresenter extends BasePresenter<HomeMvpView> {

    DataManager mDataManager;

    @Inject
    public HomePresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    public void getHomePageRequest() {
        checkViewAttached();

        String code = "REC_SOWORD_INDEX,FOCUS_INDEX,REC_CLASSIC_INDEX";
        String record = "0,0,30"; // 首次取30数据

        Map<String ,String> fileds = new HashMap<>();
        fileds.put("code", code);
        fileds.put("record", record);

        Map<String ,String> headers = new HashMap<>();
        headers.put("mac", "xxx");
        headers.put("imei", "xxxxx");

        mDataManager.getHomePageRequest(fileds, headers)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<IResponse<HomePageModel>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        getMvpView().showLoading();
                    }
                    @Override
                    public void onNext(@NonNull IResponse<HomePageModel> rankListModelIResponse) {
                        HomePageModel rankListModel = rankListModelIResponse.getEntity();
                        String code = rankListModelIResponse.getCode();
                        Log.i("wang", "onNext code:" + code);
                        List<BaseModel> items = rankListModel.recommendlist.get(0).items;
                        for (BaseModel baseModel : items) {
                            Log.i("wang", "baseModel:" + baseModel);
                        }
                        if (items != null && items.size() > 0) {
                            getMvpView().showDataLoadSuccess(items);
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
}
