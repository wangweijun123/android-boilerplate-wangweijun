package com.wangweijun.structure.injection.module;

import android.content.Context;

import com.wangweijun.structure.data.DataManager;
import com.wangweijun.structure.data.remote.StoreService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wangweijun1 on 2017/12/6.
 */

@Module
public class ApplicationModule {
    Context mContext;

    public ApplicationModule(Context context) {
        mContext = context;
    }

    @Singleton
    @Provides
    public StoreService provideStoreService() {
        return StoreService.Creator.newStoreService();
    }

    @Singleton
    @Provides
    public DataManager provideDataManager(StoreService storeService) {
        return new DataManager(storeService);
    }

    @Singleton
    @Provides
    public Context provideContext() {
        return mContext;
    }
}
