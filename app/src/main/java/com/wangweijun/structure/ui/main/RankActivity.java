package com.wangweijun.structure.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.wangweijun.structure.R;
import com.wangweijun.structure.data.DataManager;
import com.wangweijun.structure.data.model.BaseModel;
import com.wangweijun.structure.ui.base.BaseActivity;
import com.wangweijun.structure.ui.detail.DetailActivity;

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
        rankAdapter.setOnRecyclerViewItemClickListener(new RankAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v) {
                BaseModel baseModel = (BaseModel) v.getTag();
                Log.i("wang", "onItemClick baseModel:"+baseModel);
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("packagename", baseModel.packagename);
                startActivity(intent);
            }
        });
        rankAdapter.setOnRecyclerViewItemLongClickListener(new RankAdapter.OnRecyclerViewItemLongClickListener() {
            @Override
            public void onItemLongClick(View v) {
                BaseModel baseModel = (BaseModel) v.getTag();
                Log.i("wang", "onItemLongClick baseModel:"+baseModel);
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("packagename", baseModel.packagename);
                startActivity(intent);
            }
        });

        Log.i("wang", "mDataManager:"+mDataManager);

        mainPresenter.attachView(this);
        mainPresenter.getRankApps();
    }

    @Override
    public void showDataLoadSuccess(List<BaseModel> list) {
        rankAdapter.addModels(list);
    }
}
