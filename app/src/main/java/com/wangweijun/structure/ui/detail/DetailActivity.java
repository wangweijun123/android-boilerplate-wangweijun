package com.wangweijun.structure.ui.detail;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wangweijun.structure.R;
import com.wangweijun.structure.data.model.AppDetailsModel;
import com.wangweijun.structure.ui.base.BaseActivity;
import com.wangweijun.structure.ui.blacklist.BlackListActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.widget.RelativeLayout.CENTER_IN_PARENT;

/**
 * Created by wangweijun1 on 2017/12/6.
 */

public class DetailActivity extends BaseActivity implements DetailMvpView, View.OnClickListener{

    @BindView(R.id.result)
    TextView result;

    @BindView(R.id.bt)
    Button bt;

    @BindView(R.id.bt2)
    Button bt2;


    @Inject
    DetailPresenter detailPresenter;


    @BindView(R.id.root)
    RelativeLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        bt.setOnClickListener(this);
        bt2.setOnClickListener(this);
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
        switch (v.getId()) {
            case R.id.bt:
                detailPresenter.getScoreFromSP();
                break;
            case R.id.bt2:
                Intent intent = new Intent(getApplicationContext(), BlackListActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }


    @Override
    public void showLoading() {
        View loading = LayoutInflater.from(getApplicationContext()).inflate(R.layout.view_loading, null);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.addRule(CENTER_IN_PARENT);
        root.addView(loading, params);
    }

    @Override
    public void hideLoading() {
        root.removeView(root.findViewById(R.id.network_loading_pb));
    }

    @Override
    public void showErrorUI() {
        View errorContainer = LayoutInflater.from(getApplicationContext()).inflate(R.layout.view_error, null);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.addRule(CENTER_IN_PARENT);
        root.addView(errorContainer, params);

        errorContainer.findViewById(R.id.view_container).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("wang", "refresh ...");
                detailPresenter.refresh();
            }
        });
    }

    @Override
    public void hideErrorUI() {
        root.removeView(root.findViewById(R.id.view_container));
    }

}
