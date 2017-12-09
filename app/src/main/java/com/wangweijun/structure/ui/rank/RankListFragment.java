package com.wangweijun.structure.ui.rank;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.wangweijun.structure.R;
import com.wangweijun.structure.data.model.BaseModel;
import com.wangweijun.structure.ui.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.widget.RelativeLayout.CENTER_IN_PARENT;

/**
 * Created by wangweijun on 2017/12/9.
 */

public class RankListFragment extends BaseFragment implements RankListMvpView{
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.root)
    RelativeLayout root;

    @Inject
    RankListPresenter rankListPresenter;

    @Inject
    RankListAdapter rankListAdapter;

    private boolean isLoading = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View homeView = View.inflate(getActivity(), R.layout.fragment_rank_list, null);
        ButterKnife.bind(this, homeView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(rankListAdapter);

        rankListPresenter.attachView(this);
        getRankApps();
        return homeView;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        getRankApps();
    }

    private void getRankApps() {
        Log.i(TAG, this+ "  isVisibleToUser:"+isVisibleToUser+" root : " + root + ", isLoading:"+isLoading+", hasData ？"+rankListAdapter);
        // 1, 用户可见  2, view 已经初始化 3, 第一页还没加载 4, 适配器中是否已经存在数据
        if (isVisibleToUser && root != null && !isLoading && rankListAdapter != null && !rankListAdapter.hasData()) {
            rankListPresenter.getRankApps();
        }
    }

    @Override
    public void showLoading() {
        isLoading = true;
        View loading = LayoutInflater.from(getContext()).inflate(R.layout.view_loading, null);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.addRule(CENTER_IN_PARENT);
        root.addView(loading, params);
    }

    @Override
    public void hideLoading() {
        isLoading = false;
        root.removeView(root.findViewById(R.id.network_loading_pb));
    }

    @Override
    public void showErrorUI() {
        View errorContainer = LayoutInflater.from(getContext()).inflate(R.layout.view_error, null);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.addRule(CENTER_IN_PARENT);
        root.addView(errorContainer, params);

        errorContainer.findViewById(R.id.view_container).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("wang", "refresh ...");
                rankListPresenter.refresh();
            }
        });
    }

    @Override
    public void hideErrorUI() {
        root.removeView(root.findViewById(R.id.view_container));
    }

    @Override
    public void showEmptyUI() {
        View errorContainer = LayoutInflater.from(getContext()).inflate(R.layout.view_no_data, null);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.addRule(CENTER_IN_PARENT);
        root.addView(errorContainer, params);
    }

    @Override
    public void showDataLoadSuccess(List<BaseModel> list) {
        rankListAdapter.addModels(list);
    }
}
