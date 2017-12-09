package com.wangweijun.structure.ui.guide;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.wangweijun.structure.R;
import com.wangweijun.structure.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wangweijun on 2017/12/9.
 */

public class GuideActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.viewpager)
    ViewPager mainViewPager;

    @BindView(R.id.tv_nav_hot)
    TextView tv_nav_hot;

    @BindView(R.id.tv_nav_rank)
    TextView tv_nav_rank;

    @BindView(R.id.tv_nav_app)
    TextView tv_nav_app;

    @BindView(R.id.tv_nav_game)
    TextView tv_nav_game;

    @BindView(R.id.tv_nav_setting)
    TextView tv_nav_setting;

    MainPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        ButterKnife.bind(this);

        adapter = new MainPagerAdapter(getSupportFragmentManager());
        mainViewPager.setOffscreenPageLimit(5);
        mainViewPager.setAdapter(adapter);

        tv_nav_hot.setOnClickListener(this);
        tv_nav_rank.setOnClickListener(this);
        tv_nav_app.setOnClickListener(this);
        tv_nav_game.setOnClickListener(this);
        tv_nav_setting.setOnClickListener(this);

        tv_nav_hot.setSelected(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_nav_hot:
                tv_nav_hot.setSelected(true);
                tv_nav_rank.setSelected(false);
                tv_nav_app.setSelected(false);
                tv_nav_game.setSelected(false);
                tv_nav_setting.setSelected(false);
                mainViewPager.setCurrentItem(0, false);
                break;
            case R.id.tv_nav_rank:
                tv_nav_hot.setSelected(false);
                tv_nav_rank.setSelected(true);
                tv_nav_app.setSelected(false);
                tv_nav_game.setSelected(false);
                tv_nav_setting.setSelected(false);
                mainViewPager.setCurrentItem(1, false);
                break;
            case R.id.tv_nav_app:
                tv_nav_hot.setSelected(false);
                tv_nav_rank.setSelected(false);
                tv_nav_app.setSelected(true);
                tv_nav_game.setSelected(false);
                tv_nav_setting.setSelected(false);
                mainViewPager.setCurrentItem(2, false);
                break;
            case R.id.tv_nav_game:
                tv_nav_hot.setSelected(false);
                tv_nav_rank.setSelected(false);
                tv_nav_app.setSelected(false);
                tv_nav_game.setSelected(true);
                tv_nav_setting.setSelected(false);
                mainViewPager.setCurrentItem(3, false);
                break;
            case R.id.tv_nav_setting:
                tv_nav_hot.setSelected(false);
                tv_nav_rank.setSelected(false);
                tv_nav_app.setSelected(false);
                tv_nav_game.setSelected(false);
                tv_nav_setting.setSelected(true);
                mainViewPager.setCurrentItem(4, false);
                break;
        }
    }
}
