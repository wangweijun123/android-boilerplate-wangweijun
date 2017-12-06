package com.wangweijun.structure.ui.main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.wangweijun.structure.R;
import com.wangweijun.structure.data.DataManager;
import com.wangweijun.structure.data.model.BaseModel;
import com.wangweijun.structure.ui.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RankActivity extends BaseActivity implements RankMvpView {

    @Inject
    DataManager mDataManager;

    @Inject
    RankPresenter mainPresenter;

    @Inject
    RankAdapter rankAdapter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityComponent().inject(this);

        setContentView(R.layout.activity_rank);

        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(rankAdapter);


        Log.i("wang", "mDataManager:"+mDataManager);

        mainPresenter.attachView(this);
        mainPresenter.getRankApps();
    }

    @Override
    public void showDataLoadSuccess(List<BaseModel> list) {
        rankAdapter.addModels(list);
    }
}
