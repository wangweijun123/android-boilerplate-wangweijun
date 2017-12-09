package com.wangweijun.structure.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangweijun.structure.StructureApplication;
import com.wangweijun.structure.injection.component.DaggerFragmentComponent;
import com.wangweijun.structure.injection.component.FragmentComponent;

/**
 * Created by wangweijun on 2017/12/9.
 *
 * fragment生命周期总结
 * 1, 回掉的第一个方法是 setUserVisibleHint，能判断再用户眼前的是哪个fragment
 * 2，onAttach()  ---> oncreate() ---> onCreateView() ---> onResume()
 * 3, 切换fragemnt，只回掉受影响的两个fragment
 */

public class BaseFragment extends Fragment {
    public static final String TAG = "BaseFragment";

    protected boolean isVisibleToUser;

    private FragmentComponent fragmentComponent;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        fragmentComponent = DaggerFragmentComponent.builder().applicationComponent(((StructureApplication)getActivity().getApplication()).getApplicationComponent())
                .build();
        super.onCreate(savedInstanceState);
    }

    protected FragmentComponent fragmentComponent() {
        return fragmentComponent;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, this+" onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        Log.i(TAG, this+" onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.i(TAG, this+" onPause");
        super.onPause();
    }

    @Override
    public void onAttach(Activity activity) {
        Log.i(TAG, this+" onAttach");
        super.onAttach(activity);
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        this.isVisibleToUser = isVisibleToUser;
        Log.i(TAG, this+" setUserVisibleHint isVisibleToUser:"+isVisibleToUser);
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onStop() {
        Log.i(TAG, this+" onStop");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, this+" onDestroy");
        super.onDestroy();
    }
}
