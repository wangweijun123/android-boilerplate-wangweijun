package com.wangweijun.structure.injection.component;

import android.content.Context;

import com.wangweijun.structure.data.DataManager;
import com.wangweijun.structure.data.local.PreferencesHelper;
import com.wangweijun.structure.data.remote.StoreService;
import com.wangweijun.structure.injection.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by wangweijun1 on 2017/12/6.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    StoreService provideStoreService();// 告诉依赖组件自己能提供什么样的实例
    DataManager provideDataManager();// 告诉依赖组件自己能提供什么样的实例
    Context provideContext();// 告诉依赖组件自己能提供什么样的实例
    PreferencesHelper providePreferencesHelper(); //告诉依赖组件自己能提供什么样的实例
}
