package com.wangweijun.structure.ui.base;

/**
 * Created by wangweijun on 2017/12/6.
 */

public class BasePresenter<T extends MvpView> implements Presenter<T>{
    T mMvpView;

    @Override
    public void attachView(T mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        mMvpView = null;
    }

    public T getMvpView() {
        return mMvpView;
    }
}
