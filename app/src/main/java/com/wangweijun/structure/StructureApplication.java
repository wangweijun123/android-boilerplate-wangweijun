package com.wangweijun.structure;

import android.app.Application;

import com.wangweijun.structure.injection.component.ApplicationComponent;
import com.wangweijun.structure.injection.component.DaggerApplicationComponent;
import com.wangweijun.structure.injection.module.ApplicationModule;

/**
 * Created by wangweijun1 on 2017/12/6.
 */

public class StructureApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public ApplicationComponent getApplicationComponent() {
        if (applicationComponent == null) {
            applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(getApplicationContext())).build();
        }
        return applicationComponent;
    }
}
