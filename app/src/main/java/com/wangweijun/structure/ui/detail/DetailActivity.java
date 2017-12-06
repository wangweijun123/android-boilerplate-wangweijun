package com.wangweijun.structure.ui.detail;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wangweijun.structure.R;
import com.wangweijun.structure.data.model.AppDetailsModel;
import com.wangweijun.structure.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wangweijun1 on 2017/12/6.
 */

public class DetailActivity extends BaseActivity implements DetailMvpView, View.OnClickListener{

    @BindView(R.id.result)
    TextView result;
    @BindView(R.id.bt)
    Button bt;

    @Inject
    DetailPresenter detailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        bt.setOnClickListener(this);
        detailPresenter.attachView(this);
        detailPresenter.getAppDetail(getIntent().getStringExtra("packagename"));
    }

    @Override
    public void showDetailView(AppDetailsModel appDetailsModel) {
        result.setText("得分:"+appDetailsModel.score);
    }

    @Override
    public void showScore(float score) {
        bt.setText("from sp score:"+score);
    }


    @Override
    public void onClick(View v) {
        detailPresenter.getScoreFromSP();
    }
}
