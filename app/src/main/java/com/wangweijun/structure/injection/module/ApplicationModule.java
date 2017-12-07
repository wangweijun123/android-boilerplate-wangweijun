package com.wangweijun.structure.injection.module;

import android.content.Context;

import com.wangweijun.structure.data.DataManager;
import com.wangweijun.structure.data.local.db.DaoMaster;
import com.wangweijun.structure.data.local.db.DaoSession;
import com.wangweijun.structure.data.local.db.DbOpenHelper;
import com.wangweijun.structure.data.local.db.MigrationHelper;
import com.wangweijun.structure.data.local.pref.PreferencesHelper;
import com.wangweijun.structure.data.remote.StoreService;

import org.greenrobot.greendao.database.Database;

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
    public PreferencesHelper providePreferencesHelper(Context context) {
        return new PreferencesHelper(context);
    }

    @Singleton
    @Provides
    public DaoSession provideDaoSession() {
        /** A flag to show how easily you can switch from standard SQLite to the encrypted SQLCipher. */
        boolean ENCRYPTED = false;
        MigrationHelper.DEBUG = true;
        DbOpenHelper helper2 = new DbOpenHelper(mContext, ENCRYPTED ? "notes-db-encrypted" : "notes-db",
                null);
        Database db = ENCRYPTED ? helper2.getEncryptedWritableDb("super-secret") : helper2.getWritableDb();
        DaoMaster daoMaster = new DaoMaster(db);
        return daoMaster.newSession();
    }

    @Singleton
    @Provides
    public DataManager provideDataManager(StoreService storeService, PreferencesHelper preferencesHelper, DaoSession daoSession) {
        return new DataManager(storeService, preferencesHelper, daoSession);
    }

    @Singleton
    @Provides
    public Context provideContext() {
        return mContext;
    }
}
