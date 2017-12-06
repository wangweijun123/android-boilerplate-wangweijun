package com.wangweijun.structure.injection.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wangweijun1 on 2017/12/6.
 */

@Module
public class ActivityModule {
    Activity mActivity;

    public ActivityModule(Activity activity){
        mActivity = activity;
    }

    @Provides
    public Activity provideActivity() {
        return mActivity;
    }
}
