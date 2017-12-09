package com.wangweijun.structure.data;

import android.util.Log;

import com.wangweijun.structure.data.local.db.Account;
import com.wangweijun.structure.data.local.db.DaoSession;
import com.wangweijun.structure.data.local.pref.PreferencesHelper;
import com.wangweijun.structure.data.model.AppDetailsModel;
import com.wangweijun.structure.data.model.HomePageModel;
import com.wangweijun.structure.data.model.IResponse;
import com.wangweijun.structure.data.model.RankListModel;
import com.wangweijun.structure.data.remote.StoreService;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;

/**
 * Created by wangweijun1 on 2017/12/6.
 */

public class DataManager {

    private PreferencesHelper mPreferencesHelper;

    private StoreService mStoreService;

    private DaoSession mDaoSession;

    public DataManager(StoreService storeService, PreferencesHelper preferencesHelper, DaoSession daoSession) {
        mStoreService = storeService;
        mPreferencesHelper = preferencesHelper;
        mDaoSession = daoSession;
    }

    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }

    public Observable<IResponse<HomePageModel>> getHomePageRequest(Map<String, String> params,Map<String, String> headers) {
        return mStoreService.getHomePageRequest(params, headers);
    }

    public Observable<IResponse<RankListModel>> getRankApps(String pagefrom, String pagesize,String code) {
        return mStoreService.getRankApps(pagefrom, pagesize, code);
    }

    public Observable<IResponse<AppDetailsModel>> getAppDetail(String packagename) {
        return mStoreService.getAppDetail(packagename);
    }

    public Observable<Account> insertAccount(final Account account) {
        return Observable.create(new ObservableOnSubscribe<Account>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Account> emitter) throws Exception {
                mDaoSession.getAccountDao().insert(account);
                emitter.onComplete();
            }
        });
    }

    public Observable insertAccounts(final List<Account> accounts) {
        return Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(@NonNull ObservableEmitter emitter) throws Exception {
                Log.i("wang", "subscribe tid:"+Thread.currentThread().getId());
                // 开启事务批量插入
                mDaoSession.getAccountDao().insertInTx(accounts);
                emitter.onComplete();
            }
        });
    }

    public Observable<List<Account>> queryAccounts() {
        return Observable.create(new ObservableOnSubscribe<List<Account>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<Account>> emitter) throws Exception {
                List<Account> accounts = mDaoSession.getAccountDao().queryBuilder().build().list();
                emitter.onNext(accounts);
                emitter.onComplete();
            }
        });
    }

}
