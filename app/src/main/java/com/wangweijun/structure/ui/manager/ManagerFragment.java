package com.wangweijun.structure.ui.manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.wangweijun.structure.R;
import com.wangweijun.structure.ui.base.BaseFragment;
import com.wangweijun.structure.ui.login.LoginActivity;
import com.wangweijun.structure.ui.login.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wangweijun on 2017/12/9.
 */

public class ManagerFragment extends BaseFragment implements View.OnClickListener{
    @BindView(R.id.login)
    Button login;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View homeView = View.inflate(getActivity(), R.layout.fragment_manager, null);
        ButterKnife.bind(this, homeView);
        login.setOnClickListener(this);
        EventBus.getDefault().register(this);
        return homeView;
    }

    @Override
    public void onClick(View v) {
        getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe
    public void mainThreadMode(MessageEvent event) {
        Toast.makeText(getContext(), event.message, Toast.LENGTH_SHORT).show();
    }
}
