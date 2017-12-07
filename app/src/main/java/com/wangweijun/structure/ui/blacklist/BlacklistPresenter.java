package com.wangweijun.structure.ui.blacklist;

import android.util.Log;

import com.wangweijun.structure.data.DataManager;
import com.wangweijun.structure.data.local.db.Account;
import com.wangweijun.structure.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wangweijun1 on 2017/12/7.
 */

public class BlacklistPresenter extends BasePresenter<BlacklistMvpView> {

    private final DataManager mDataManager;

    @Inject
    public BlacklistPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    public void insertAccount(Account account) {
        mDataManager.insertAccount(account)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Account>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i("wang", "onSubscribe tid:"+Thread.currentThread().getId());
                    }

                    @Override
                    public void onNext(@NonNull Account account) {
                        Log.i("wang", "onNext tid:"+Thread.currentThread().getId());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("wang", "onError tid:"+Thread.currentThread().getId());
                    }

                    @Override
                    public void onComplete() {
                        Log.i("wang", "onComplete tid:"+Thread.currentThread().getId());
                    }
                });
    }

    public void insertAccounts(List<Account> accounts) {
        mDataManager.insertAccounts(accounts)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Object o) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.i("wang", "onComplete tid:"+Thread.currentThread().getId());
                    }
                });
    }


    public void queryAccounts() {
        mDataManager.queryAccounts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Account>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i("wang", "onSubscribe tid:"+Thread.currentThread().getId());
                    }

                    @Override
                    public void onNext(@NonNull List<Account> accounts) {
                        for (Account account : accounts) {
                            Log.i("wang", account.toString());
                        }
                        getMvpView().showDataLoadSuccess(accounts);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("wang", "onError tid:"+Thread.currentThread().getId());
                    }

                    @Override
                    public void onComplete() {
                        Log.i("wang", "onComplete tid:"+Thread.currentThread().getId());
                    }
                });
    }
}
