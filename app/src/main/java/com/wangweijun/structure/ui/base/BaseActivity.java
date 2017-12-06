package com.wangweijun.structure.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wangweijun.structure.StructureApplication;
import com.wangweijun.structure.injection.component.ActivityComponent;
import com.wangweijun.structure.injection.component.DaggerActivityComponent;
import com.wangweijun.structure.injection.module.ActivityModule;

/**
 * Created by wangweijun1 on 2017/12/6.
 */

public class BaseActivity extends Activity{
    ActivityComponent activityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(((StructureApplication)getApplication()).getApplicationComponent())
                .activityModule(new ActivityModule()).build();

    }

    public ActivityComponent activityComponent() {
        return activityComponent;
    }
}
