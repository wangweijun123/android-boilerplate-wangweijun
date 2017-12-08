package com.wangweijun.structure.ui.detail;

import android.util.Log;

import com.wangweijun.structure.data.DataManager;
import com.wangweijun.structure.data.model.AppDetailsModel;
import com.wangweijun.structure.data.model.IResponse;
import com.wangweijun.structure.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wangweijun1 on 2017/12/6.
 */

public class DetailPresenter extends BasePresenter<DetailMvpView> {

    private final DataManager mDataManager;

    String mPackagename;
    @Inject
    DetailPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    public void getAppDetail(String packagename) {
        checkViewAttached();
        mPackagename = packagename;
        mDataManager.getAppDetail(packagename)
                .subscribeOn(Schedulers.io())
                .doOnNext(new Consumer<IResponse<AppDetailsModel>>() {
                    @Override
                    public void accept(@NonNull IResponse<AppDetailsModel> appDetailsModelIResponse) throws Exception {
                        mDataManager.getPreferencesHelper().setScore(appDetailsModelIResponse.getEntity().score);
                        Log.i("wang", "accept setScore "+ ", tid:" + Thread.currentThread().getId());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<IResponse<AppDetailsModel>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        getMvpView().showLoading();
                    }

                    @Override
                    public void onNext(@NonNull IResponse<AppDetailsModel> appDetailsModelIResponse) {
                        AppDetailsModel appDetailsModel = appDetailsModelIResponse.getEntity();
                        Log.i("wang", "onNext score:" + appDetailsModel.score + ", tid:" + Thread.currentThread().getId());

                        getMvpView().showDetailView(appDetailsModel);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        getMvpView().hideLoading();
                        getMvpView().showErrorUI();
                    }

                    @Override
                    public void onComplete() {
                        getMvpView().hideLoading();
                    }
                });
    }


    public void getScoreFromSP() {
        checkViewAttached();
        Observable<Float> observable = Observable.create(new ObservableOnSubscribe<Float>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Float> emitter) throws Exception {
                float score = mDataManager.getPreferencesHelper().getScore();
                Log.i("wang", "subscribe score:" + score + ", tid:" + Thread.currentThread().getId());
                emitter.onNext(score);
                emitter.onComplete();
            }
        });
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Float>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Float s) {
                        Log.i("wang", "onNext s:" + s + ", tid:" + Thread.currentThread().getId());
                        getMvpView().showScore(s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    public void refresh() {
        getMvpView().hideErrorUI();
        getAppDetail(mPackagename);
    }


}
