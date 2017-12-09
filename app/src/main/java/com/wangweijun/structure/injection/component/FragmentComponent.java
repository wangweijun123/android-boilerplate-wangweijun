package com.wangweijun.structure.injection.component;

import com.wangweijun.structure.injection.PerActivity;
import com.wangweijun.structure.ui.game.GameFragment;
import com.wangweijun.structure.ui.home.HomeFragment;
import com.wangweijun.structure.ui.rank.RankListFragment;
import com.wangweijun.structure.ui.soft.SoftFragment;

import dagger.Component;

/**
 * Created by wangweijun1 on 2017/12/6.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class)
public interface FragmentComponent {

    void inject(HomeFragment homeFragment);

    void inject(RankListFragment rankListFragment);

    void inject(SoftFragment softFragment);

    void inject(GameFragment gameFragment);



}
