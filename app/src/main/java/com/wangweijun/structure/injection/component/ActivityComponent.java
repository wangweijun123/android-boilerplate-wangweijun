package com.wangweijun.structure.injection.component;

import com.wangweijun.structure.injection.PerActivity;
import com.wangweijun.structure.injection.module.ActivityModule;
import com.wangweijun.structure.ui.blacklist.BlackListActivity;
import com.wangweijun.structure.ui.detail.DetailActivity;
import com.wangweijun.structure.ui.main.RankActivity;

import dagger.Component;

/**
 * Created by wangweijun1 on 2017/12/6.
 */
@PerActivity
@Component(modules = ActivityModule.class, dependencies = ApplicationComponent.class)
public interface ActivityComponent {

    void inject(RankActivity mainActivity);

    void inject(DetailActivity detailActivity);

    void inject(BlackListActivity blackListActivity);
}
