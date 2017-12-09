package com.wangweijun.structure.ui.manager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangweijun.structure.R;
import com.wangweijun.structure.ui.base.BaseFragment;

/**
 * Created by wangweijun on 2017/12/9.
 */

public class ManagerFragment extends BaseFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View homeView = View.inflate(getActivity(), R.layout.fragment_manager, null);
        return homeView;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }
}
