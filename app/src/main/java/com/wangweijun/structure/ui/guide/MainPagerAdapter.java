package com.wangweijun.structure.ui.guide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wangweijun.structure.ui.game.GameFragment;
import com.wangweijun.structure.ui.home.HomeFragment;
import com.wangweijun.structure.ui.manager.ManagerFragment;
import com.wangweijun.structure.ui.rank.RankListFragment;
import com.wangweijun.structure.ui.soft.SoftFragment;

/**
 * Created by wangweijun on 2017/12/9.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {
    private Class fragmentArray[] = {HomeFragment.class,
            RankListFragment.class, SoftFragment.class,
            GameFragment.class, ManagerFragment.class};

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return fragmentArray.length;
    }

    @Override
    public Fragment getItem(int index) {
        Fragment f = null;
        try {
            f = (Fragment) Class.forName(fragmentArray[index].getName()).newInstance();
            if (index == 0) {
                Bundle bundle = new Bundle();
                f.setArguments(bundle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }
}